package com.orange.sync.strategy;

import com.orange.sync.enums.ApiEnum;
import com.orange.sync.service.PlayerService;
import com.orange.sync.service.RoomService;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

public class LeaveRoomStrategy implements IMessage {


    @Override
    public String getName() {
        return ApiEnum.API_LeaveRoom.name();
    }

    @Override
    public void doDispatcherMsg(String msg, Channel channel) {

        if (!channel.isActive()) {
            System.out.println("链接已经关闭，无法进行创建");
            return;
        }
        Long playerId = PlayerService.getPlayerByChannel(channel);
        RoomService.removePlayerByPlayer(playerId);
        PlayerService.updatePlayerToLeaveRoom(playerId);
        MessageUtil.sendApi(ApiEnum.API_LeaveRoom, null);
        MessageUtil.sendMsg2All(ApiEnum.MSG_RoomSync,RoomService.listRoom());
    }


}
