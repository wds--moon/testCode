package com.moon.io.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wendongshan
 * 按道理说客户端不需要知道客户端那个已经上线了,只接受消息即可
 * 服务器会吧每一个发的消息转发给任何一个客户端
 */
public class SimpleChatClientHandler  extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println( s + "\n");
    }
}
