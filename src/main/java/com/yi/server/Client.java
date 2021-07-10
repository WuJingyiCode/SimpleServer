package com.yi.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
        start("127.0.0.1", 50101);
    }

    public static void start(String ip, int port) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(ip, port));
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byte[] bytes = "Hello SimpleServer".getBytes();
            for (byte aByte : bytes) {
                System.out.print(aByte);
            }
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
