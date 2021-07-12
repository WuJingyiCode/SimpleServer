package com.yi.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private TCPSelector tcpSelector;

    public static void main(String[] args) {
        Server server = new Server();
        server.start("127.0.0.1", 50101);
    }

    private void start(String ip, int port) {

        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(ip, port));
            selector = Selector.open();
            tcpSelector = new TCPSelector();
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int selectNum = selector.select();
                if (selectNum != 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        iterator.remove();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        tcpSelector.register(socketChannel);
                        tcpSelector.select();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {

    }
}
