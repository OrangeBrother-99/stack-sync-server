package com.orange.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.orange.sync.entity.Player;
import com.orange.sync.entity.Room;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

import java.util.Objects;

public class FrameSyncService {


    public static void sync(JSONObject jsonObject , Channel channel) {

        long frameId = jsonObject.getLong("frameId");
        String data = jsonObject.getString("data");

        Long playerId = PlayerService.getPlayerByChannel(channel);
        if (Objects.isNull(playerId)|| playerId <0){
            return;
        }
        Player player = PlayerService.getPlayer(playerId);
        //判断该玩家是否加入房间（向房间推送）
        if (Objects.isNull(player)){
            return;
        }
        if (Objects.nonNull(player.getRoomId())){
            Room room = RoomService.getRoom(player.getRoomId());
            room.getRoomService().input(data,playerId,frameId);
        }else{
            MessageUtil.sendMsg2All(ApiEnum.MSG_ServerSync,data);
        }

    }
}
