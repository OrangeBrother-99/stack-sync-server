package com.orange.sync.service;

import com.orange.sync.entity.Player;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

import java.util.*;

public class PlayerService {

    private static long nextPlayerId;
    private static final Set<Player> players = new HashSet<>();
    //玩家的房间号在该结构内维护，其余无法获取
    private static final Map<Long, Player> playerMap = new HashMap<>();
    private static final Map<Channel, Long> channelMap = new HashMap<>();

    public static Player createPlayer(String name, Channel channel) {

        Player player = new Player(++nextPlayerId, name, channel);
        if (players.contains(player)) {
            return null;
        }
        players.add(player);
        channelMap.put(channel, player.getId());
        playerMap.put(player.getId(), player);
        return player;
    }

//    public static void removePlayer(long playerId) {
//        Player player = playerMap.get(playerId);
//        if (Objects.nonNull(player)) {
//            players.remove(player);
//            playerMap.remove(playerId);
//        }
//    }

    public static void removePlayerByChannel(Channel channel) {
        Long playerId = channelMap.getOrDefault(channel, null);
        if (Objects.isNull(playerId)) {
            return;
        }
        Player player = playerMap.get(playerId);
        if (Objects.nonNull(player)) {
            System.out.println("玩家退出游戏》 roomId:"+player.getRoomId() + " playerId: "+ player.getNickName());
            PlayerService.updatePlayerToLeaveRoom(playerId);
            playerMap.remove(playerId);
            channelMap.remove(channel);
            players.remove(player);
            MessageUtil.sendMsg2All(ApiEnum.MSG_PlayerSync,PlayerService.listPlayers());
        }
        System.out.println("当前服务器人数：" + players.size());
    }


    public static Player getPlayer(Long playerId) {
        return playerMap.get(playerId);
    }

    public static Set<Player> listPlayers() {
        return players;
    }

    public static Long getPlayerByChannel(Channel channel) {
        return Objects.nonNull(channelMap.get(channel)) ? channelMap.get(channel) : null;
    }

    public static void updatePlayerToLeaveRoom(Long playerId) {
        Player player = getPlayer(playerId);
        if (Objects.isNull(player)){
            return;
        }
        System.out.println("玩家"+player.getId()+"离开房间:"+ player.getRoomId());
        player.setRoomId(null);
        playerMap.put(playerId,player);
    }
}
