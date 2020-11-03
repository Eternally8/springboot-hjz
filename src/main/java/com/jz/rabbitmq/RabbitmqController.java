package com.jz.rabbitmq;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(tags = "rabbitmq使用")
//@RestController
//@RequestMapping("/rabbitMq")
//public class RabbitmqController {
//    @Autowired
//    private Sender sender;
//    @Autowired
//    private SenderAck senderAck;
//
//    @GetMapping("send")
//    public String send(){
//        sender.send();
//        return "success";
//    }
//
//    @GetMapping("send2")
//    public String send2(){
//        sender.send2();
//        return "success";
//    }
//
//    @GetMapping("acksend2")
//    public String acksend2(){
//        senderAck.ackSend();
//        return "success";
//    }
//
//
//    @GetMapping("topicSend1")
//    public String topicSend1(){
//        sender.topicSend1();
//        return "success";
//    }
//
//    @GetMapping("topicSend2")
//    public String topicSend2(){
//        sender.topicSend2();
//        return "success";
//    }
//
//}
