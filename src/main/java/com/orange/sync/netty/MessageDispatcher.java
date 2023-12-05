package com.orange.sync.netty;

import com.orange.sync.enums.ApiEnum;
import com.orange.sync.strategy.StrategyManger;
import com.orange.sync.strategy.IMessage;
import io.netty.channel.Channel;

import java.util.Objects;

public class MessageDispatcher {



    public boolean  doDispatcher(String name, String data, Channel channel) {
        if (Objects.isNull(name) || Objects.isNull(data) || Objects.isNull(channel)) {
            return false;
        }
        ApiEnum apiEnum = ApiEnum.getInstance(name);
        if (Objects.isNull(apiEnum)){
            return false;
        }
        if (!StrategyManger.exit(name)){
           return false;
        }
        IMessage dispatcher = StrategyManger.getFactory(name);
        dispatcher.doDispatcherMsg(data, channel);
        return  true;
    }
}
