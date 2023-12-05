package com.orange.sync.service;

import com.alibaba.fastjson.JSONObject;
import com.orange.sync.entity.Player;
import com.orange.sync.entity.Room;
import com.orange.sync.entity.TimePast;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.enums.InputEnum;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class RoomSyncService {
    private List<JSONObject> input;
    private Long roomId;

    private BigDecimal frame = new BigDecimal(1).divide(new BigDecimal(60), 3, RoundingMode.HALF_UP);

    private Map<Long,Long> lastFrameIdMap;

    public RoomSyncService(Long roomId) {
        this.roomId = roomId;
        this.input = new ArrayList<>();
        this.schedule();
        this.scheduleTime();
        this.lastFrameIdMap = new HashMap<>();
    }

    public void input(String request,  Long playerId, long frameId) {
        input.add(JSONObject.parseObject(request));
        lastFrameIdMap.put(playerId,frameId);
    }
    public void input(String request) {
        input.add(JSONObject.parseObject(request));
    }
    public void schedule() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Room room = RoomService.getRoom(roomId);
                if (Objects.isNull(room)) {
                  return;
                }
                if (room.waitStart()) {
                  return;
                }
                pushInput(room);
            }
        },0,100);


    }

    private void pushInput(Room room) {
        if (input.isEmpty()) {
            return;
        }
        List<JSONObject> temp = input;
        input = new ArrayList<>();
        //数据结构：ServerSync.java
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",temp);
        Set<Player> players = room.getPlayers();
        List<Channel> channelStream = players.stream().map(Player::getChannel).collect(Collectors.toList());

        for (Channel channel: channelStream) {
            map.put("frameId",lastFrameIdMap.get(PlayerService.getPlayerByChannel(channel)));
            MessageUtil.sendMsg2AppointOne(ApiEnum.MSG_ServerSync, map, channel);
        }
    }

    private void scheduleTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Room room = RoomService.getRoom(roomId);
                if (Objects.isNull(room)) {
                    return;
                }
                if (room.waitStart()) {
                    return;
                }
                TimePast timePast = new TimePast(frame, InputEnum.TimePast.name());
                input(JSONObject.toJSONString(timePast));
                pushInput(room);
            }
        },0,16);



    }


}
