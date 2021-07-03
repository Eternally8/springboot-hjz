package com.mq.consumer;

import com.mq.config.RocketMqContants;
import com.mq.entity.UserVo;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/7/3 21:46
 */
@Service
@RocketMQMessageListener(topic = RocketMqContants.Topic,consumerGroup = RocketMqContants.consumerGroup,selectorExpression = "*")
public class DemoConsumer implements RocketMQListener<UserVo> {


    @Override
    public void onMessage(UserVo userVo) {
        System.out.println("消费了消息:" + userVo.toString());
    }

}
