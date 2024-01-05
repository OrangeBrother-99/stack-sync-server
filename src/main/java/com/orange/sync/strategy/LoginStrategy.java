package com.orange.sync.strategy;

import com.alibaba.fastjson.JSONObject;
import com.orange.sync.entity.Player;
import com.orange.sync.enums.ApiEnum;
import com.orange.sync.service.PlayerService;
import com.orange.sync.utils.MessageUtil;
import io.netty.channel.Channel;
import io.netty.util.internal.StringUtil;

public class LoginStrategy implements IMessage {


    @Override
    public String getName() {
        return ApiEnum.API_Login.name();
    }

    @Override
    public void doDispatcherMsg(String msg, Channel channel) {

        //解密数据
        JSONObject json = JSONObject.parseObject(msg);
        String name = json.getString("nick");
        if (StringUtil.isNullOrEmpty(name)) {
            MessageUtil.sendApi(ApiEnum.API_Login, "登录失败，请输入昵称");
            return;
        }

        Player player = PlayerService.createPlayer(name, channel);
        MessageUtil.sendApi(ApiEnum.API_Login, player);
        System.out.println("创建角色完成");
        MessageUtil.sendMsg2All(ApiEnum.MSG_PlayerSync, PlayerService.listPlayers());
    }


}
