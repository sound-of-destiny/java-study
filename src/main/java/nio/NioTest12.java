package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest12 {
    public static void main(String[] args) throws IOException {
        int[] port = new int[5];
        port[0] = 5000;
        port[1] = 5001;
        port[2] = 5002;
        port[3] = 5003;
        port[4] = 5004;

        Selector selector = Selector.open();

        for (int i = 0; i < port.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port[i]);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口: " + port[i]);
        }

        while(true) {
            int num = selector.select();
            System.out.println("num: " + num);

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            System.out.println("selectionKeys: " + selectionKeySet);

            Iterator<SelectionKey> it = selectionKeySet.iterator();

            while(it.hasNext()) {
                SelectionKey selectionKey = it.next();

                if(selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    it.remove();

                    System.out.println("获得客户端连接: " + socketChannel);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int byteRead = 0;

                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read <= 0) {
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteRead += read;
                    }
                    System.out.println("读取: " + byteRead + ", 来自于: " + socketChannel);
                    it.remove();
                }
            }
        }

    }
}
