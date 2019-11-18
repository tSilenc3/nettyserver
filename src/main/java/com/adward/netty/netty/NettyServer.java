package com.adward.netty.netty;

import com.adward.netty.netty.message.PacketType;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${netty.server.port}")
    private int port;

    private ChildChannelHandler childChannelHandler;

    private EventLoopGroup boss = new NioEventLoopGroup(1);
    private EventLoopGroup work = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    @Autowired
    public NettyServer(ChildChannelHandler childChannelHandler) {
        this.childChannelHandler = childChannelHandler;
    }

    public void start() {
        logger.info("服务开始启动");
        PacketType.initPacket();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(this.port)
                    .childHandler(this.childChannelHandler);

            bootstrap.bind().sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
