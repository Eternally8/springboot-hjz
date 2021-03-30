package com.kafka.controller;

import com.kafka.constant.KakfaConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "生产者发送")
@Slf4j
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate kafkaTemplate;


    @ApiOperation("简单发送")
    @GetMapping("/send")
    public String send(String params) {
        log.info("开始发送请求:{}",params);
        
        kafkaTemplate.send(KakfaConstant.topic1, params);

        log.info("请求发送完成:{}",params);
        return "success";
    }


    @ApiOperation("发送有回执")
    @GetMapping("/sendAck")
    public String sendAck(String params) {
        log.info("开始发送请求:{}",params);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KakfaConstant.topic1, params);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.error("Kafka发送消息失败：Topic={},errMsg={}", KakfaConstant.topic1, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                //成功的处理
                log.info("Kafka发送消息成功：Topic={},msg={}", KakfaConstant.topic1, result.toString());
            }
        });

        log.info("请求发送完成:{}",params);
        return "success";
    }


    @ApiOperation("事务发送")
    @GetMapping("/transactionSend")
    public String transactionSend(String params) {
        log.info("开始发送请求:{}",params);

        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send(KakfaConstant.topic1,params);
            int a = 0;
            int b = 1/a;
            return "faile";
        });

        return "success";
    }


    @ApiOperation("事务发送2")
    @GetMapping("/transactionSend2")
    @Transactional(rollbackFor = RuntimeException.class)
    public String transactionSend2(String params) {
        log.info("开始发送请求:{}",params);

        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.send(KakfaConstant.topic1,params);
        throw new RuntimeException("failed");
    }


}
