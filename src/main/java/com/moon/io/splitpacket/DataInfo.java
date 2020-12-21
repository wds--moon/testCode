package com.moon.io.splitpacket;


/**
 * 自定义协议包
 * 服务器和客户端必须按照这个模式传递
 * 严格来说content 也应该是一个像PageHelper那样使用标准模式
 */
public class DataInfo {

    //定义一次发送包体长度
    private int len;
    //一次发送包体内容
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
