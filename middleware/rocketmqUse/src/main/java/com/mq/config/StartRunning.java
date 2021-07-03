package com.mq.config;

import com.mq.entity.UserVo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Description：
 * 如果发送不了,注意云机器的防火墙和安全组、conf/broker.conf 是否设置了brokerIP1=xxx.xxx.xxx.xxx（服务器公网地址）
 * namesrv启动方式：
 *  nohup sh bin/mqnamesrv &
 * broker启动方式：
 *  nohup sh bin/mqbroker -n xxx.xxx.xxx.xxx（服务器公网地址）:9876 -c conf/broker.conf autoCreateTopicEnable=true &
 * Author: hujingzheng
 * Date: 2021/7/3 19:46
 */
@Component
public class StartRunning implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {
        String msgStr = "hello hahahahhaha";

        rocketMQTemplate.convertAndSend("topic1", msgStr);
        rocketMQTemplate.convertAndSend("topic1", new UserVo("hjz",18));

        System.out.println("sendMsg~~~~~~");
    }

}
