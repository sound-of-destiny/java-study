package other;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;

public class EStest {

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
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("数据库错误");
                }
            }
        } while (conn == null);
        return conn;
    }

    private TransportClient client;

    public void setUp() throws Exception {

        Settings esSettings = Settings.builder()
                .put("cluster.name", "schong-es") //设置ES实例的名称
                .put("client.transport.sniff", true) //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .build();

        client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("202.194.14.106"), 9300));

        System.out.println("ElasticsearchClient 连接成功");
    }

    public void clientDown() {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) {
        new EStest().run();
    }

    private void run() {
        try {
            setUp();
            Connection conn = openDb();
            Statement stm = conn.createStatement();
            ArrayList<String> codigos = new ArrayList<>();
            ArrayList<Integer> commodityIds = new ArrayList<>();
            ResultSet res = stm.executeQuery("SELECT codigo,commodityId FROM supnuevo_statistics.supnuevo_common_commodity where modifyTime > 20180101");
            while (res.next()) {
                codigos.add(res.getString(1));
                commodityIds.add(res.getInt(2));
            }
            stm.close();
            int num = codigos.size();
            for (int i = 0; i < num; i++) {
                SearchResponse sr = client.prepareSearch()
                        .setQuery(QueryBuilders.termQuery("codigo", codigos.get(i)))
                        .addAggregation(AggregationBuilders.terms("prices").field("price"))
                        .setSize(100)
                        .get();
                Terms prices = sr.getAggregations().get("prices");
                Statement stm1 = conn.createStatement();
                for (Terms.Bucket entry : prices.getBuckets()) {
                    double price = (double) entry.getKey();
                    int count = (int) entry.getDocCount();
                    stm1.execute(String.format("insert into supnuevo_statistics.supnuevo_now_price_count_all (commodityId,codigo,nowprice,count) values (%d,'%s',%s,%d)", commodityIds.get(i), codigos.get(i), price, count));
                }
                stm1.close();
            }
            conn.close();
            clientDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
