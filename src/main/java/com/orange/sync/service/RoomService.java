package com.orange.sync.service;

import com.orange.sync.entity.Player;
import com.orange.sync.entity.Room;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

import java.util.*;

public class RoomService {

    private static long nextRoomId;
    private static final Set<Room> rooms = new HashSet<>();
    private static final Map<Long, Room> roomMap = new HashMap<>();

    public static Room createRoom() {

        Room room = new Room(++nextRoomId);
        if (rooms.contains(room)) {
            return null;
        }
        rooms.add(room);
        roomMap.put(room.getRoomId(), room);
        return room;
    }

    public static boolean joinRoom(Long playerId, Long roomId) {
        if (!roomMap.containsKey(roomId)) {
            System.out.println("房间不存在");
            return false;
        }
        Player player = PlayerService.getPlayer(playerId);
        if (Objects.isNull(player)) {
            System.out.println("玩家信息不存在");
            return false;
        }

        Room room = roomMap.get(roomId);
        if (room.getPlayers().contains(player) || Objects.nonNull(player.getRoomId())) {
            System.out.println("请勿重复加入房间");
            return false;
        }
        player.setRoomId(roomId);
        room.getPlayers().add(player);
        return true;
    }


    public static void removePlayerByChannel(Channel channel) {
        Long playerId = PlayerService.getPlayerByChannel(channel);
        removePlayerByPlayer(playerId);
    }

    public static void removePlayerByPlayer(Long playerId) {
        if (Objects.isNull(playerId)){
            return;
        }
        Player player = PlayerService.getPlayer(playerId);
        if (Objects.nonNull(player) && Objects.nonNull(player.getRoomId())) {
            Long roomId = player.getRoomId();
            if (!roomMap.containsKey(roomId)) {
                return;
            }
            Room room = roomMap.get(roomId);
            room.getPlayers().remove(player);
            if (room.getPlayers().size()<1) {
                //销毁房间
                rooms.remove(room);
                roomMap.remove(roomId);
                MessageUtil.sendMsg2All(ApiEnum.MSG_RoomSync,listRoom());
                return;
            }
            MessageUtil.sendMsg2All(ApiEnum.MSG_Room, room);
        }
    }

    public static Set<Room> listRoom() {
        return rooms;
    }

    public static Room getRoom(Long roomId) {
        return roomMap.get(roomId);
    }

//    public static  void startGame(Long roomId){
//        Room room = getRoom(roomId);
//        if (Objects.isNull(room)){
//            return;
//        }
//        Set<Player> players = room.getPlayers();
//        //组装数据
//
//
//    }
}
