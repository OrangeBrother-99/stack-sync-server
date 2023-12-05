package com.orange.sync.entity;

import com.orange.sync.service.RoomSyncService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {

    private Long roomId;
    private Set<Player> players;
    private transient RoomSyncService roomService;

    // 0 未开始、1已开始
    private Integer state;

    public Room(Long roomId) {
        this.roomId = roomId;
        this.players = new HashSet<>();
        this.roomService = new RoomSyncService(roomId);
        this.state = 0;
    }

    public RoomSyncService getRoomService() {
        return roomService;
    }

//    public void setRoomService(RoomSyncService roomService) {
//        this.roomService = roomService;
//    }
//
//    public void setPlayers(Set<Player> players) {
//        this.players = players;
//    }

    public Long getRoomId() {
        return roomId;
    }

//    public void setRoomId(Long roomId) {
//        this.roomId = roomId;
//    }

    public Set<Player> getPlayers() {
        return players;
    }


    public List<PlayerState> startBattle() {
        List<PlayerState> states = new ArrayList<>();
        for (Player player : players) {
            states.add(PlayerState.create(player.getId()));
        }
        return states;
    }

    public void startGame() {
        this.state = 1;
    }

    public boolean waitStart() {
        return this.state == 0;
    }
}
