package com.orange.sync.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelSupervise {
    private static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ConcurrentMap<String, ChannelId> ChannelMap = new ConcurrentHashMap();

    public static void addChannel(Channel channel) {
        GlobalGroup.add(channel);
        ChannelMap.put(channel.id().asShortText(), channel.id());
    }

    public static void removeChannel(Channel channel) {
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
    }

    public static Channel findChannel(String id) {
        return GlobalGroup.find(ChannelMap.get(id));
    }

    public static void send2All(Object tws) {
        String json = JSON.toJSONString(tws);
        // 将数据转换为字节数组
        byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
        // 创建 BinaryWebSocketFrame，并发送给客户端
        // 创建 ByteBuf，并将字节数组写入
        ByteBuf responseBuf = Unpooled.wrappedBuffer(responseBytes);

        // 创建 BinaryWebSocketFrame，并发送给客户端
        BinaryWebSocketFrame responseFrame = new BinaryWebSocketFrame(responseBuf);
        GlobalGroup.writeAndFlush(responseFrame);
    }

    public static void send2Appoint(Object message, List<Channel> list) {
        String json = JSON.toJSONString(message);
        // 将数据转换为字节数组
        byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
        // 创建 BinaryWebSocketFrame，并发送给客户端
        ByteBuf responseBuf = Unpooled.wrappedBuffer(responseBytes);
        BinaryWebSocketFrame responseFrame = new BinaryWebSocketFrame(responseBuf);
        list.forEach(c -> c.writeAndFlush(responseFrame));
    }

}
