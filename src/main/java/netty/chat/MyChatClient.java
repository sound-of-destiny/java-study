package netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClient {
    public static void main(String[] args) throws Exception {
        EpollEventLoopGroup eventLoopGroup = new EpollEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(eventLoopGroup).channel(EpollSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = b.connect("localhost", 8899).sync().channel();
            //channelFuture.channel().closeFuture().sync();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for(;;) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
