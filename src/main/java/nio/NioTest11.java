package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest11 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength) {
                long r = socketChannel.read(byteBuffers);
                byteRead += r;

                System.out.println("byteRead: " + byteRead);

                Arrays.stream(byteBuffers).map(byteBuffer -> "position: " + byteBuffer.position() + ", limit: " + byteBuffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);

            long byteWritten = 0;
            while (byteWritten < messageLength) {
                long r = socketChannel.write(byteBuffers);
                byteWritten += r;
            }

            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);
        }
    }
}
