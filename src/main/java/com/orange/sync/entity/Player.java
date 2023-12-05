package com.orange.sync.entity;


import io.netty.channel.Channel;


public class Player {


    private Long id;
    private String nickName;
    private transient Channel channel;
    private Long roomId;



    public Player(Long id, String nickName, Channel channel) {
        this.id = id;
        this.nickName = nickName;
        this.channel = channel;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
