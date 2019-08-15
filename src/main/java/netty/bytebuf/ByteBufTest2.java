package netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest2 {
    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        ByteBuf heapBuf = Unpooled.buffer();
        ByteBuf directBuf = Unpooled.directBuffer();
        compositeByteBuf.addComponents(heapBuf, directBuf);

        //compositeByteBuf.removeComponent(0);

        compositeByteBuf.forEach(System.out::println);
    }
}
