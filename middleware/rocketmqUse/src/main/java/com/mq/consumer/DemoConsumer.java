package com.mq.consumer;

import com.mq.entity.UserVo;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/7/3 21:46
 */
@Service
@RocketMQMessageListener(topic = "topic3",consumerGroup = "consumer_group_3")
public class DemoConsumer implements RocketMQListener<UserVo> {


    @Override
    public void onMessage(UserVo userVo) {
        System.out.println("消费了消息:" + userVo);
    }

}
