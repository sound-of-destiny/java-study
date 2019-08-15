package zeroCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TraditionalClient {

    public static void main(String[] args) {
    	int port = 2000;
		String server = "localhost";
		Socket socket = null;
		int ERROR = 1;

		// connect to server
		try {
	    	socket = new Socket(server, port);
	    	System.out.println("Connected with server " +
				   socket.getInetAddress() +
				   ":" + socket.getPort());
		} catch (Exception e) {
			e.printStackTrace();
	    	System.exit(ERROR);
		}

		String fname = "sendfile";
		try (DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		 	FileInputStream inputStream = new FileInputStream(fname)) {
	    	long start = System.currentTimeMillis();
	    	byte[] b = new byte[4096];
	    	long read = 0, total = 0;
	    	while((read = inputStream.read(b)) >= 0) {
				total = total + read;
	    		output.write(b);
	    	}
	    	System.out.println("bytes send--" + total + " and totaltime--" + (System.currentTimeMillis() - start));
		} catch (IOException e) {
	    	e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
