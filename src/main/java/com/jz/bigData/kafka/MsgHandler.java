package com.jz.bigData.kafka;//package com.jz.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MsgHandler {
//
//    private String topicValue = TopicNameList.topic1;
//
//    //以下两种的方式都可以来接受请求
////    @KafkaListener(topics = { "test-topic" })
////    public void handle(String message) {
////        System.out.println("[ 处理器开始处理消息1 ]" + System.currentTimeMillis());
////        System.out.println(message);
////        System.out.println("[ 处理器处理消息完成1 ]" + System.currentTimeMillis());
////    }
//
//    @KafkaListener(topics = { "test-topic" })
//    public void handle(ConsumerRecord<String, String> record) {
//        System.out.println("[ 处理器开始处理消息2 ]" + System.currentTimeMillis());
//        System.out.println("record:"+record);
//        System.out.println("[ 处理器处理消息完成2 ]" + System.currentTimeMillis());
//    }
//
//    @KafkaListener(topics = "user")
//    public void consumer(ConsumerRecord consumerRecord){
//        System.out.println("[ 处理器开始处理消息3 ]" + System.currentTimeMillis());
//        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
//        if(kafkaMassage.isPresent()){
//            Object o = kafkaMassage.get();
//            System.out.println(o);
//        }
//    }


}
