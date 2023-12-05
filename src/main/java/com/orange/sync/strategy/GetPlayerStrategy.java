package com.orange.sync.strategy;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.service.PlayerService;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;

public class GetPlayerStrategy implements IMessage {


    @Override
    public String getName() {
        return ApiEnum.API_PlayerList.name();
    }

    @Override
    public void doDispatcherMsg(String msg, Channel channel) {
        MessageUtil.sendApi(ApiEnum.API_PlayerList ,PlayerService.listPlayers() );
    }


}
