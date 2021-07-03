package com.mq.consumer;

import com.mq.entity.UserVo;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/7/3 21:46
 */
@Service
public class DemoConsumer implements RocketMQListener<UserVo> {


    @Override
    public void onMessage(UserVo userVo) {

    }

}
