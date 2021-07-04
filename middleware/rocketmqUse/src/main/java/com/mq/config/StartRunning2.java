package com.mq.config;

import cn.hutool.core.date.SystemClock;
import com.mq.entity.UserVo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description：
 *  队列内有序,队列外无序
 *
 * Date: 2021/7/3 19:46
 */
@Order(2)
@Component
public class StartRunning2 implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    

    @Override
    public void run(String... args) throws Exception {
        long time = SystemClock.now()/1000;

        UserVo userVo = new UserVo("hjz" + time, 18);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~发送消息完毕2~~~~~~~~~~~~~~~~~~~~");
    }

}
