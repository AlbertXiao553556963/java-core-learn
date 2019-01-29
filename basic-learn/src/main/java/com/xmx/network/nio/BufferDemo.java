package com.xmx.network.nio;

import java.nio.ByteBuffer;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-23 21:16
 **/
public class BufferDemo {
    public static void main(String[] args) {
        // allocate 4 byte jvm memory
        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);

        // allocate 4 byte direct memory
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        // write 1 byte data
        byteBuffer.put((byte) 1);



        // adjust position limit , trans to read mode
        byteBuffer.flip();

        // read 1 byte
        byte value = byteBuffer.get();

        // clear the read data , trans to write mode
        byteBuffer.compact();
    }

}
