package com.moon.io.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<DataInfo> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i< 200; i++) {
            String msg = "dota现在已经没人玩！";
            //创建协议包对象
            DataInfo info = new DataInfo();
            info.setLen(msg.getBytes(CharsetUtil.UTF_8).length);
            info.setContent(msg.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(info);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
