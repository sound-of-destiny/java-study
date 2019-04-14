package nio;

import java.nio.ByteBuffer;

public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putInt(15);
        byteBuffer.putLong(5000000000L);
        byteBuffer.putDouble(14.2342);
        byteBuffer.putChar('你');
        byteBuffer.putShort((short)2);
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }
}
