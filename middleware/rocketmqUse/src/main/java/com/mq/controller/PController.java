package com.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/p")
public class PController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @GetMapping(value = "/send")
    public String getUser1(){
        String msgStr = "hello hahahahhaha";

        rocketMQTemplate.convertAndSend("topic1", msgStr);

        return "success";
    }



}
