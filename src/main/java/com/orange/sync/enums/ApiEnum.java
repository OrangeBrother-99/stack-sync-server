package com.orange.sync.enums;

import java.util.Arrays;

public enum ApiEnum {
    API_Login,
    API_PlayerList,
    API_CreateRoom,
    API_RoomList,
    API_LeaveRoom,
    API_JoinRoom,
    API_StartGame,
    MSG_PlayerSync,
    MSG_ServerSync,
    MSG_ClientSync,
    MSG_Room,
    MSG_RoomSync,
    MSG_GameStart,
    ;

    public  static  ApiEnum  getInstance(String name){
        return  Arrays.stream(values()).filter(v-> v.name().equals(name)).findFirst().orElse(null);
    }



}
