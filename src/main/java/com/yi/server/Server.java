package com.yi.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static void main(String[] args) {
        start("127.0.0.1", 50101);
    }

    public static void start(String ip, int port) {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.bind(new InetSocketAddress(ip, port));
            while (true) {
                SocketChannel clientSocket = socketChannel.accept();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = clientSocket.read(byteBuffer);
                byteBuffer.flip();
                int remaining = byteBuffer.remaining();
                if (remaining > 0) {
                    byte[] result = new byte[1024];
                    byteBuffer.get(result, 0, remaining);
                    System.out.println("Server reading:" + new String(result));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
