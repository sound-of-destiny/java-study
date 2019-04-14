package Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            while (true) {
                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                String a = "我是你爸爸";
                pout.println(a);

                int b = Runtime.getRuntime().availableProcessors();

                System.out.println(b);
                client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}

