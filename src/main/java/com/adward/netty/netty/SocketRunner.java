package com.adward.netty.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SocketRunner implements CommandLineRunner {

    private NettyServer nettyServer;

    @Autowired
    public SocketRunner(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }
}
