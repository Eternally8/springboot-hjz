package com.mq.consumer;

import com.mq.config.RocketMqContants;
import com.mq.entity.UserVo;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/7/3 21:46
 */
@Service
@RocketMQMessageListener(topic = "topic13",consumerGroup = "batchcg1",selectorExpression = "*")
public class BatchConsumer implements RocketMQListener<List<Message<UserVo>>> {


    @Override
    public void onMessage(List<Message<UserVo>> list) {
        for (Message vo : list) {
            System.out.println("消费了消息:" + vo.toString());
        }

    }

}
