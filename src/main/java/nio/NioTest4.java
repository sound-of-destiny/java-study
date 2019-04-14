package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        while(true) {
            buffer.clear();
            System.out.println("before remaining " + buffer.remaining());
            int read  = inputChannel.read(buffer);
            System.out.println("read " + read);
            System.out.println("after remaining " + buffer.remaining());
            if (-1 == read) break;
            Thread.sleep(1000);

            buffer.flip();
            outputChannel.write(buffer);

        }

        inputStream.close();
        outputStream.close();
    }
}
