package com.moon.io.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 消息和客户端处理类
 * 这个类相当于我们扩展的一个ChannelInboundHandler ,里面ChannelInboundHandlerAdapter 作为适配器
 */
public class SimpleChatServerHandler  extends SimpleChannelInboundHandler<String> {
    /**
     * 把所有的链接客户加入链接组,如果是应用级别这里可能要使用缓存数据库保存所有链接的客户端
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 游客户端链接的时候处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("客户端 - "+incoming.remoteAddress()+" 上线\n ");
        }
        channels.add(incoming);
    }

    /**
     * 客户端退出的时候消息处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("客户端 - "+incoming.remoteAddress()+" 退出\n ");
        }
        channels.remove(incoming);
    }

    /**
     * 读取消息,服务器转发消息给所有的客户端
     * 这里可以设置只给某一个人发送消息,当然这个s字符串需要序列化为一个固定的对象,这个对象客户端和服务端都按照这个规则传递消息
     * url等于null的时候发送给全部,否则只发给某一个人
     * {
     *    "url": "/127.0.0.1:62780",
     *    "msg": "你好呀!",
     * }
     *
     * 可以指定给谁发消息
     * ,服务器转发的时候只转发给他一个人就行
     * @param chx
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext chx, String s) throws Exception {
        Channel incoming = chx.channel();
        for (Channel channel : channels) {
            if (channel != incoming){
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]:" + s + "\n");
            } else {
                channel.writeAndFlush("[自己]:" + s + "\n");
            }
        }
    }

    /**
     * 是否在线监听
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("客户端:"+incoming.remoteAddress()+"在线");
    }

    /**
     * 掉线和退出不同,掉线是网络监听,退出是主动发起退出
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("客户端:"+incoming.remoteAddress()+"掉线");
    }

    /**
     * 程序异常
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
        Channel incoming = ctx.channel();
        System.out.println("客户端:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
