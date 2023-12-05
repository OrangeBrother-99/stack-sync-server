package com.orange.sync.utils;

import com.alibaba.fastjson.JSON;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.netty.ChannelSupervise;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.StringUtil;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    public static void sendMsg2All(ApiEnum apiEnum, Object object) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("name",apiEnum.name() );
        returnMap.put("data", object);
        // 群发
        ChannelSupervise.send2All(returnMap);
    }
    public static void sendMsg2Appoint(ApiEnum apiEnum, Object object, List<Channel> channel) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("name",apiEnum.name() );
        returnMap.put("data", object);
        //指定列表
        ChannelSupervise.send2Appoint(returnMap, channel);
    }
    public static void sendMsg2AppointOne(ApiEnum apiEnum, Object object,Channel channel) {
        ArrayList<Channel> list = new ArrayList<>();
        list.add(channel);
        sendMsg2Appoint(apiEnum,object, list);
    }

    public static void sendApi(ApiEnum apiEnum,Object object) {
        sendApi(object,apiEnum,"");
    }

    public static void sendApiError(ApiEnum apiEnum,String error) {
        sendApi(null,apiEnum,error);
    }

    public static void sendApi(Object object,ApiEnum apiEnum,String error) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("name",apiEnum.name() );
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("success", StringUtil.isNullOrEmpty(error));
        dataMap.put("res",object);
        dataMap.put("error",error);
        returnMap.put("data", dataMap);
        // 群发
        ChannelSupervise.send2All(returnMap);
    }
}
