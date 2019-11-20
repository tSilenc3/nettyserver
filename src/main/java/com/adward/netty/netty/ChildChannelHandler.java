package com.adward.netty.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChildChannelHandler extends ChannelInitializer {

    @Autowired
    NettyIOHandler nettyIOHandler;

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline().addLast(new HttpServerCodec());
        channel.pipeline().addLast(new ChunkedWriteHandler());
        channel.pipeline().addLast(new HttpObjectAggregator(65536));
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/", "", true, 1064 * 64));
        channel.pipeline().addLast(new LengthFieldPrepender(4));
        // 客户端300秒没收发包，便会触发UserEventTriggered事件到MessageTransportHandler
        channel.pipeline().addLast("idleStateHandler", new IdleStateHandler(0, 0, 30));
        channel.pipeline().addLast(nettyIOHandler);
    }
}
