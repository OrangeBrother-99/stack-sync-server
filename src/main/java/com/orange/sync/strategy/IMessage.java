package com.orange.sync.strategy;

import io.netty.channel.Channel;

public interface IMessage {

      String getName();


      void doDispatcherMsg(String msg, Channel channel);
}
