package com.orange.sync.strategy;

import com.orange.sync.enums.ApiEnum;

import java.util.HashMap;
import java.util.Map;

public class StrategyManger {
    private static Map<String, IMessage> messageMap = new HashMap<>();

    static {
        messageMap.put(ApiEnum.API_Login.name(), new LoginStrategy());
        messageMap.put(ApiEnum.API_PlayerList.name(), new GetPlayerStrategy());
        messageMap.put(ApiEnum.API_CreateRoom.name(), new RoomStrategy());
        messageMap.put(ApiEnum.API_RoomList.name(),  new GetRoomStrategy());
        messageMap.put(ApiEnum.API_JoinRoom.name(),  new JoinRoomStrategy());
        messageMap.put(ApiEnum.API_LeaveRoom.name(),  new LeaveRoomStrategy());
        messageMap.put(ApiEnum.API_StartGame.name(),  new GameStartStrategy());
    }


    public static IMessage getFactory(String name) {
        return messageMap.get(name);
    }
    public static boolean exit(String name) {
        return messageMap.containsKey(name);
    }
}
