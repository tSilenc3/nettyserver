package com.adward.netty.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketManager;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.adward.netty.netty.utils.SessionUtil;
import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@ChannelHandler.Sharable
public class NettyIOHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("收到请求了" + ctx.channel().localAddress());
        ChannelPool.getInstance().add(ctx.channel());
        if (!SessionUtil.addChannelSession(ctx.channel(), new IoSession(ctx.channel()))) {
            ctx.close();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        logger.info("收到消息了" + textWebSocketFrame.text());
        String message = textWebSocketFrame.text();

        JSONObject jsonObject = JSON.parseObject(message);
        int cmd = jsonObject.getInteger("cmd");

        AbstractPacket packet = PacketManager.INSTANCE.createNewPacket(cmd, jsonObject);

        Channel channel = channelHandlerContext.channel();
        IoSession ioSession = SessionUtil.getSessionByChannel(channel);

        PacketManager.INSTANCE.execPacket(ioSession, packet);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开连接了");
        ChannelPool.getInstance().remove(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("逻辑出错了");
        super.exceptionCaught(ctx, cause);
    }
}
