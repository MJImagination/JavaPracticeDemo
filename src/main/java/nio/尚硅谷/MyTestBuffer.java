package nio.尚硅谷;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/5/22
 */
public class MyTestBuffer {
    @Test
    public void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("1:" + byteBuffer.position());
        System.out.println("2:" + byteBuffer.limit());
        System.out.println("3:" + byteBuffer.capacity());

        String str = "abc";
        byteBuffer.put(str.getBytes());

        System.out.println("1:" + byteBuffer.position());
        System.out.println("2:" + byteBuffer.limit());
        System.out.println("3:" + byteBuffer.capacity());

        System.out.println("1:" + byteBuffer.position());
        System.out.println("2:" + byteBuffer.limit());
        System.out.println("3:" + byteBuffer.capacity());

        byteBuffer.flip();
        byteBuffer.position(1);
//        byteBuffer.get(byteBuffer.array(), 1, byteBuffer.limit());
        byteBuffer.get(byteBuffer.array());
        byteBuffer.get(byteBuffer.array(),0,1);
        byte[] dst = new byte[byteBuffer.limit()];
//        byteBuffer.get(dst,0,1);
        System.out.println(new String(dst));

    }





}
