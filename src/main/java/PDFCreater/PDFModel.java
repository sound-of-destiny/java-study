package PDFCreater;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PDFModel {
    public static void main(String[] args) throws IOException, DocumentException {
        String templatefile = "D:\\Idea\\JavaTest\\cs2.pdf";
        String destfile = "HelloWorld1.pdf";

        PdfReader reader = new PdfReader(templatefile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);

        //BaseFont bf = BaseFont.createFont(BaseFont.RESOURCE_PATH,BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        //Font fontChinese = null;
        BaseFont baseFont = BaseFont.createFont("C:\\WINDOWS\\Fonts\\simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //fontChinese = new Font(baseFont, 12,Font.NORMAL);
        AcroFields s = ps.getAcroFields();
        s.setFieldProperty("stuName", "textfont", baseFont, null);
        s.setFieldProperty("stuNum", "textfont", baseFont, null);

        s.setField("stuName", "舒冲冲");
        s.setField("stuNum", "201734879");

        //设置为true/false在点击生成的pdf文档的填充域时有区别，
        ps.setFormFlattening(true);
        ps.close();
        //最终生成的文件，
        FileOutputStream fos = new FileOutputStream(destfile);
        fos.write(bos.toByteArray());

        //new PDFModel().getPdfFile(templatefile,destfile,databean);
    }

    public void getPdfFile(String templatefile, String destfile, DataBean databean) throws IOException, DocumentException {
        String TemplatePDF = templatefile;
        PdfReader reader = new PdfReader(TemplatePDF);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destfile));
        AcroFields form = stamper.getAcroFields();
        DataBean db = databean;
        List<String> fieldnames = this.getFieldName(db);
        for (int i = 0; i < fieldnames.size(); i++) {
            String tmpname = fieldnames.get(i);
            String value = this.getFieldValue(tmpname, db);
            form.setField(tmpname, value);
        }
        stamper.setFormFlattening(true);
        stamper.close();
    }

    private List<String> getFieldName(DataBean db) {
        List<String> fieldnames = new ArrayList<String>();
        Field[] fields = db.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String tmpname = fields[i].getName();
            fieldnames.add(tmpname);
        }
        return fieldnames;
    }

    private String getFieldValue(String fieldname, DataBean db) {
        String value = "";
        Method[] methods = db.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodname = methods[i].getName();
            if (methodname.substring(0, 3).toUpperCase().equals("GET") && methodname.substring(3).toUpperCase().equals(fieldname.toUpperCase())) {
                Method method = methods[i];
                try {
                    value = (String) method.invoke(db, new Object[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
