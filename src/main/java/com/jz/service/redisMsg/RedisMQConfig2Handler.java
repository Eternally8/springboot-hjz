package com.jz.service.redisMsg;

public interface RedisMQConfig2Handler {

    void handleMessage(String msg);

    String redisChannel();

}
