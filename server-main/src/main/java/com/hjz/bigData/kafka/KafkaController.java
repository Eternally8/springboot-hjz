package com.hjz.bigData.kafka;//package com.hjz.kafka;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "kafka")
@RestController
public class KafkaController {
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
    private String topic = TopicNameList.topic1;

    @GetMapping("/send")
    public String send(String params) {
        System.out.println("开始发送请求:"+params);

//        kafkaTemplate.send(topic, params);

        System.out.println("请求发送完成:"+params);
        return "success";
    }
}
