# play-for-scala
运用play框架实现简单的登录注册功能，所有操作都是全异步操作。

用JQuery ajax 提交，数据格式为json，使用[天气接口](http://api.openweathermap.org/data/2.5/weather?q=Hangzhou&APPID=db9fe8bd7dd365794a091c26e9b7d2c5)获取天气，登入后调转到用户首页显示用户名和当前用户和天气情况。

最后实现基于 WebSocket 的聊天功能

+ 登入后显示最近20条聊天记录
+ 一旦有用户发送了消息则在所有在线的用户显示该消息

