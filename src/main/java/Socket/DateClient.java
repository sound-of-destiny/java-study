package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Locale;

public class DateClient {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("211.87.225.209", 6013);
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = bin.readLine()) != null) System.out.println(line);
            sock.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
