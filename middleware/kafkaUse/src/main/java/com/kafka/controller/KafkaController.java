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
    @Transactional
    public String send(String params) {
        log.info("开始发送请求:{}",params);
        
        kafkaTemplate.send(KakfaConstant.topic1, params);

        //kafka允许为每条消息设置一个key，一旦消息被定义了 Key，那么就可以保证同一个 Key 的所有消息都进入到相同的分区，
        // 这种策略属于自定义策略的一种，被称作"按消息key保存策略"，或Key-ordering 策略。
        kafkaTemplate.send(KakfaConstant.topic1,"key11", params);

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


    @ApiOperation("事务正常发送")
    @GetMapping("/transactionSend")
    public String transactionSend(String params) {
        log.info("开始发送请求:{}",params);

        kafkaTemplate.executeInTransaction(operations -> {
            operations.send(KakfaConstant.topic1,params);
            return "lalalal";
        });

        return "success";
    }

    @ApiOperation("事务异常发送-模板方法")
    @GetMapping("/transactionSend2")
    public void transactionSend2(String params){
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send(KakfaConstant.topic1,params);
            throw new RuntimeException("fail");
        });
    }

    @ApiOperation("事务异常发送-注解")
    @GetMapping("/transactionSend3")
    @Transactional
    public void transactionSend3(String params){
        kafkaTemplate.send(KakfaConstant.topic1, params);
        throw new RuntimeException("fail");
    }

}
