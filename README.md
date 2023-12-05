# stack-sync-server
帧同步-服务端（房间对战）
#服务端采用Netty实现
##通信：websocket
##实现功能
> 角色：创建角色、异常退出游戏

> 房间：创建房间、加入房间、离开房间

> 开始游戏

#基础设计
>通信机制：通知客户端分为：全用户推送、房间内推送

>客户端请求受理：消息提交、API提交

#逻辑实现
> com.orange.sync.netty.ChannelSupervise 存储每一次客户端链接channel 

> com.orange.sync.netty.MessageDispatcher 用于处理客户端的api请求分发

> com.orange.sync.strategy 具体api服务实现目录

> com.orange.sync.service  处理游戏对局中相关实体状态

> com.orange.sync.utils 工具包 

> com.orange.sync.NettyServer 服务端启动类