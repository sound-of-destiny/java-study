package other;

import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatToCSV {

    public static void main(String[] args) {
        new DatToCSV().excute();
    }

    private void excute() {
        int startDate = 20180512;
        int endDate = 20180819;
        deletetimes(startDate, endDate);
        deleteMer(startDate, endDate);
        toCSV(startDate, endDate);
        deleteCodigo();
        dataExcute();
        dataExcuteJSON();
        dataExcuteARFF();
        //toMysql();
        //toMysqlCount();
    }

    private static Connection openDb() {
        Connection conn = null;
        do {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/supnuevo_statistics", "root",
                        "root");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException sqle) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("数据库错误");
                }
            }
        } while (conn == null);
        return conn;
    }

    private void toCSV(int startDate, int endDate) {
        String path = "/home/schong/Documents/Data/UploadData";
        String outpath = "/home/schong/Documents/codigo/";
        String datetime;
        for (; endDate > startDate; endDate--) {
            datetime = endDate + "";
            File file = new File(path + "/" + datetime);
            File[] merList = file.listFiles();
            if (merList != null) {
                for (File mer : merList) {
                    try {
                        String[] codigos;
                        int[] cods;
                        double[] prics;
                        int len;
                        Integer cod = null;
                        Double price = null;
                        String[] merchantN = mer.getName().split("\\.");
                        String merchant = merchantN[0];
                        if (merchantN.length == 1) {
                            File[] timesList = mer.listFiles();
                            if (timesList != null) {
                                for (File times : timesList) {
                                    FileInputStream in = new FileInputStream(times);
                                    ObjectInputStream oin = new ObjectInputStream(in);
                                    codigos = (String[]) oin.readObject();
                                    cods = (int[]) oin.readObject();
                                    prics = (double[]) oin.readObject();
                                    len = oin.readInt();
                                    oin.close();

                                    for (int j = 0; j < len; j++) {
                                        if (j >= codigos.length)
                                            continue;
                                        if (j < cods.length)
                                            cod = cods[j];
                                        if (j < prics.length)
                                            price = prics[j];
                                        if (price != 0) {
                                            DecimalFormat df = new DecimalFormat("#.0");
                                            double nowPrice = Double.parseDouble(df.format(price));
                                            File csv = new File(outpath + codigos[j] + ".csv");
                                            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
                                            bw.write(nowPrice + "," + merchant + "," + datetime);
                                            bw.newLine();
                                            bw.close();
                                        }
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void deleteCodigo() {
        ArrayList<String> codigoList = new ArrayList<>();
        try {
            Connection conn = openDb();
            Statement stm = conn.createStatement();
            String sql = "SELECT codigo FROM supnuevo_statistics.supnuevo_common_commodity where modifyTime > 20180101";
            ResultSet codigoRes = stm.executeQuery(sql);
            while (codigoRes.next()) {
                codigoList.add(codigoRes.getString(1));
            }

            stm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int codigoLen = codigoList.size();
        String path = "/home/schong/Documents/codigo";
        File file = new File(path);
        File[] coList = file.listFiles();
        for (File codi : coList) {
            String codiS = codi.getName().split("\\.")[0];
            int j = 0;
            for (String co : codigoList) {
                j = j + 1;
                if (codiS.equals(co)) break;
                if (j == codigoLen) {
                    codi.delete();
                }
            }
        }
    }

    private void deletetimes(int startDate, int endDate) {
        String path = "/home/schong/Documents/Data/UploadData";
        String datetime;
        for (; endDate > startDate; endDate--) {
            datetime = endDate + "";
            File file = new File(path + "/" + datetime);
            File[] merList = file.listFiles();
            if (merList != null) {
                for (File mer : merList) {
                    File[] timesList = mer.listFiles();
                    if (timesList != null) {
                        int last = timesList.length;
                        for (File times : timesList) {
                            int name = Integer.parseInt(times.getName().split("\\.")[0]);
                            if (name < last) {
                                times.delete();
                            }
                        }
                    }
                }
            }
        }
    }

    private void deleteMer(int startDate, int endDate) {
        ArrayList<Integer> merchantList = new ArrayList<>();
        try {
            Connection conn = openDb();
            Statement stm = conn.createStatement();
            String sql1 = "SELECT distinct merchantId FROM supnuevo_statistics.supnuevo_merchant_info a join supnuevo_statistics.supnuevo_province_city b where b.cityId=a.cityId and (b.provinceId=2 or b.provinceId=3);";
            ResultSet merchantIdRes = stm.executeQuery(sql1);
            while (merchantIdRes.next()) {
                merchantList.add(merchantIdRes.getInt(1));
            }

            stm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int merchantLen = merchantList.size();
        String path = "/home/schong/Documents/Data/UploadData";

        String datetime;
        for (; endDate > startDate; endDate--) {
            datetime = endDate + "";
            File file = new File(path + "/" + datetime);
            File[] merList = file.listFiles();
            if (merList != null) {
                for (File mer : merList) {
                    int merId = Integer.parseInt(mer.getName().split("\\.")[0]);
                    int j = 0;
                    for (int m : merchantList) {
                        j = j + 1;
                        if (merId == m) break;
                        if (j == merchantLen) {
                            File[] timesList = mer.listFiles();
                            if (timesList != null) {
                                for (File times : timesList) {
                                    times.delete();
                                }
                            }
                            mer.delete();
                        }
                    }
                }
            }
        }
    }

    private void dataExcute() {
        try {
            String path = "/home/schong/Documents/codigo/";
            String outPath = "/home/schong/Documents/newCodigo/";
            File f = new File(path);
            File[] lf = f.listFiles();
            assert lf != null;
            for (File codigof : lf) {
                String codigo = codigof.getName().split("\\.")[0];
                String str;
                FileInputStream inputStream = new FileInputStream(codigof);
                BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
                ArrayList<Integer> merList = new ArrayList<>();
                while ((str = buf.readLine()) != null) {
                    int merchantId;
                    double price;
                    String lastmodifyDate;
                    String[] tsql = str.split(",");
                    price = Double.parseDouble(tsql[0]);
                    merchantId = Integer.parseInt(tsql[1]);
                    lastmodifyDate = tsql[2];
                    if (merList.isEmpty()) {
                        merList.add(merchantId);
                        File csv = new File(outPath + codigo + ".csv");
                        BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
                        bw.write(price + "," + merchantId + "," + lastmodifyDate);
                        bw.newLine();
                        bw.close();
                    } else {
                        int j = 0;
                        for (int mer : merList) {
                            j = j + 1;
                            int merLen = merList.size();
                            if (merchantId == mer) break;
                            if (j == merLen) {
                                merList.add(merchantId);
                                File csv = new File(outPath + codigo + ".csv");
                                BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
                                bw.write(price + "," + merchantId + "," + lastmodifyDate);
                                bw.newLine();
                                bw.close();
                                break;
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dataExcuteJSON() {
        try {
            String path = "/home/schong/Documents/newCodigo/";
            String outPathJSON = "/home/schong/Documents/newCodigoJSON/";
            File f = new File(path);
            File[] lf = f.listFiles();
            assert lf != null;
            for (File codigof : lf) {
                String codigo = codigof.getName().split("\\.")[0];
                String str;
                FileInputStream inputStream = new FileInputStream(codigof);
                BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
                File csv = new File(outPathJSON + codigo + ".json");
                BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
                while ((str = buf.readLine()) != null) {
                    int merchantId;
                    double price;
                    String lastmodifyDate;
                    String[] tsql = str.split(",");
                    price = Double.parseDouble(tsql[0]);
                    merchantId = Integer.parseInt(tsql[1]);
                    lastmodifyDate = tsql[2];
                    String year = lastmodifyDate.substring(0, 4);
                    String month = lastmodifyDate.substring(4, 6);
                    String day = lastmodifyDate.substring(6, 8);
                    String timestamp = year + "-" + month + "-" + day + "T09:00:00.000Z";
                    bw.write("{\"index\":{\"_index\":\"supnuevo-price-history\"}}");
                    bw.newLine();
                    bw.write("{\"@timestamp\":\"" + timestamp + "\",\"codigo\":\"" + codigo + "\",\"price\":" + price + ",\"merchant\":" + merchantId + "}");
                    bw.newLine();
                }
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dataExcuteARFF() {
        try {
            String path = "/home/schong/Documents/newCodigo/";
            String outPathARFF = "/home/schong/Documents/newCodigoARFF/";
            File f = new File(path);
            File[] lf = f.listFiles();
            assert lf != null;
            for (File codigof : lf) {
                String codigo = codigof.getName().split("\\.")[0];
                String str;
                FileInputStream inputStream = new FileInputStream(codigof);
                BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
                File arff = new File(outPathARFF + codigo + ".arff");
                BufferedWriter bw = new BufferedWriter(new FileWriter(arff, true));
                bw.write("@RELATION " + codigo);
                bw.newLine();
                bw.newLine();
                bw.write("@ATTRIBUTE price REAL");
                bw.newLine();
                bw.newLine();
                bw.write("@DATA");
                bw.newLine();

                while ((str = buf.readLine()) != null) {
                    String[] tsql = str.split(",");
                    double price = Double.parseDouble(tsql[0]);
                    bw.write(price + "");
                    bw.newLine();
                }

                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toMysql() {
        try {
            Connection conn = openDb();
            Statement stms = conn.createStatement();
            String sqls = "truncate table supnuevo_now_price";
            stms.execute(sqls);
            stms.close();
            String path = "/home/schong/Documents/newCodigo/";
            File f = new File(path);
            File[] fl = f.listFiles();
            String str;
            for (File codigoF : fl) {
                String codigo = codigoF.getName().split("\\.")[0];
                FileInputStream fis = new FileInputStream(codigoF);
                BufferedReader buf = new BufferedReader(new InputStreamReader(fis));
                while ((str = buf.readLine()) != null) {
                    Statement stm = conn.createStatement();
                    int commodityId = 0;
                    ResultSet res = stm.executeQuery("select commodityId from supnuevo_common_commodity where codigo =" + codigo);
                    while (res.next()) {
                        commodityId = res.getInt(1);
                    }
                    int merchantId;
                    double price;
                    String lastmodifyDate;
                    String[] tsql = str.split(",");
                    price = Double.parseDouble(tsql[0]);
                    merchantId = Integer.parseInt(tsql[1]);
                    lastmodifyDate = tsql[2];
                    String sql = String.format("insert supnuevo_now_price (commodityId,codigo,merchantId,nowprice,lastmodifyDate) values (%d,'%s',%d,%s,'%s')", commodityId, codigo, merchantId, price, lastmodifyDate);
                    stm.execute(sql);
                    stm.close();
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toMysqlCount() {
        try {
            Connection conn = openDb();
            Statement stms = conn.createStatement();
            String sqls = "truncate table supnuevo_now_price_count";
            stms.execute(sqls);
            stms.close();
            String path = "/home/schong/Documents/newCodigo/";
            File f = new File(path);
            File[] fl = f.listFiles();
            String str;
            assert fl != null;
            for (File codigoF : fl) {
                String codigo = codigoF.getName().split("\\.")[0];
                FileInputStream fis = new FileInputStream(codigoF);
                BufferedReader buf = new BufferedReader(new InputStreamReader(fis));
                HashMap<Double, Integer> priceList = new HashMap<>();
                int commodityId = 0;
                Statement stm = conn.createStatement();
                ResultSet res = stm.executeQuery("select commodityId from supnuevo_common_commodity where codigo =" + codigo);
                while (res.next()) {
                    commodityId = res.getInt(1);
                }
                stm.close();

                while ((str = buf.readLine()) != null) {
                    int count = 1;
                    String[] tsql = str.split(",");
                    double price = Double.parseDouble(tsql[0]);
                    if (priceList.isEmpty()) {
                        priceList.put(price, count);
                    } else {
                        if (priceList.get(price) == null) {
                            priceList.put(price, count);
                        } else {
                            count++;
                            priceList.put(price, count);
                        }
                    }
                }

                for (Map.Entry<Double, Integer> entry : priceList.entrySet()) {
                    double price = entry.getKey();
                    int count = entry.getValue();
                    Statement stml = conn.createStatement();
                    String sql = String.format("insert supnuevo_now_price_count (commodityId,codigo,nowprice,count) values (%d,'%s',%s,%d)", commodityId, codigo, price, count);
                    stml.execute(sql);
                    stml.close();
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
