package com.yi.server;

import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientSocketChannels {
    private List<SocketChannel> socketChannels;

    public ClientSocketChannels() {
        socketChannels = new CopyOnWriteArrayList<>();
    }

    public void add(SocketChannel socketChannel) {
        socketChannels.add(socketChannel);
    }

    public List<SocketChannel> getSocketChannels() {
        return socketChannels;
    }
}
