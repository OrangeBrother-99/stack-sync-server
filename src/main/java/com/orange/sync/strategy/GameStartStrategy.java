package com.orange.sync.strategy;

import com.alibaba.fastjson.JSONObject;
import com.orange.sync.entity.PlayerState;
import com.orange.sync.entity.Room;
import com.orange.sync.entity.State;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.service.RoomService;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Objects;

public class GameStartStrategy implements IMessage {


    @Override
    public String getName() {
        return ApiEnum.API_StartGame.name();
    }

    @Override
    public void doDispatcherMsg(String msg, Channel channel) {

        if (!channel.isActive()) {
            System.out.println("链接已经关闭，无法进行创建");
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(msg);
        Long roomId = jsonObject.containsKey("roomId") ? jsonObject.getLong("roomId") : 0L;
        Room room = RoomService.getRoom(roomId);
        if (Objects.isNull(room)) {
            MessageUtil.sendApiError(ApiEnum.API_StartGame,"房间已销毁无法加入");
            return;
        }
        room.startGame();
        List<PlayerState> actors = room.startBattle();
        State state = new State(actors);

        MessageUtil.sendApi(ApiEnum.API_StartGame,"");
        MessageUtil.sendMsg2All(ApiEnum.MSG_GameStart,state);

    }


}
