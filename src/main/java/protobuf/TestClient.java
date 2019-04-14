package protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;

public class TestClient {
    public static void main(String[] args) throws  Exception {
        EventLoopGroup eventLoopGroup = new EpollEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(eventLoopGroup).channel(EpollSocketChannel.class)
                    .handler(new MyClientInitializer());

            ChannelFuture channelFuture = b.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
