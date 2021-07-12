package com.yi.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPSelector {
    private Selector selector;

    public TCPSelector() throws IOException {
        selector = Selector.open();
    }

    public void register(SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void select() throws IOException {
        int selectNum = selector.select();
        if (selectNum <= 0) {
            return;
        }
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
            iterator.remove();
            SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            clientChannel.read(byteBuffer);
            byteBuffer.flip();
            int remaining = byteBuffer.remaining();
            if (remaining > 0) {
                byte[] result = new byte[1024];
                byteBuffer.get(result, 0, remaining);
                System.out.println("Server reading:" + new String(result));
            }
        }
        selectionKeys.clear();
    }
}
