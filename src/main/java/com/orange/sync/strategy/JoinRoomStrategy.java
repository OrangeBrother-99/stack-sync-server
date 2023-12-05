package com.orange.sync.strategy;

import com.alibaba.fastjson.JSONObject;
import com.orange.sync.entity.Room;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.service.PlayerService;
import com.orange.sync.service.RoomService;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

import java.util.Objects;

public class JoinRoomStrategy implements IMessage {


    @Override
    public String getName() {
        return ApiEnum.API_JoinRoom.name();
    }

    @Override
    public void doDispatcherMsg(String msg, Channel channel) {

        if (!channel.isActive()) {
            System.out.println("链接已经关闭，无法进行创建");
            return;
        }
        Long playerId = PlayerService.getPlayerByChannel(channel);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        Long roomId = jsonObject.containsKey("roomId") ? jsonObject.getLong("roomId") : 0L;
        Room room = RoomService.getRoom(roomId);
        if (Objects.isNull(room)) {
            return;
        }
        boolean result = RoomService.joinRoom(playerId, room.getRoomId());
        if (!result) {
            MessageUtil.sendApiError(ApiEnum.API_CreateRoom, "无法进入房间");
            return;
        }
        System.out.println("加入房间完成");
        MessageUtil.sendApi(ApiEnum.API_JoinRoom, room);
        MessageUtil.sendMsg2All(ApiEnum.MSG_RoomSync, RoomService.listRoom());
        MessageUtil.sendMsg2All(ApiEnum.MSG_Room, room);
    }


}
