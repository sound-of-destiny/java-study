package other;

import java.io.*;

public class PythonTest {
    public static void main(String args[]) throws IOException, InterruptedException {
        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);
        String[] arg = new String[]{"python", currentPath + "/src/T_Crawler.py"};
        Process pr = Runtime.getRuntime().exec(arg);
        BufferedReader bf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = bf.readLine()) != null) {
            System.out.println(line);
        }
        bf.close();
        pr.waitFor();
    }
}
