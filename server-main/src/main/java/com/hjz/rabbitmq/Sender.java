package com.hjz.rabbitmq;

//@Slf4j
//@Component
//public class Sender {
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    /**
//     * 轮询消费模式
//     * @return
//     */
//    public String send(){
//        String context = "今天是晴天";
//        log.info("开始发送消息:{},time:{}",context,new Date());
//
//        amqpTemplate.convertAndSend(RabbitmqConfig.QUEUE_1, context);
//        return "发送成功";
//    }
//
//    public String send2(){
//        MqMsgVo MqMsgVo = new MqMsgVo(); //实现Serializable接口
//        MqMsgVo.setId(1101);
//        MqMsgVo.setName("lalalallala");
//        MqMsgVo.setCreateTimestamp(System.currentTimeMillis());
//
//        log.info("开始发送消息:{},time:{}", JSON.toJSONString(MqMsgVo),new Date());
//
//        amqpTemplate.convertAndSend(RabbitmqConfig.QUEUE_2, MqMsgVo);
//        return "发送成功";
//    }
//
//    /**
//     *  TOPIC模式
//     */
//    public void topicSend1(){
//        String context = "hi, i am message 1";
//        amqpTemplate.convertAndSend(RabbitmqConfig.TopicExchange,RabbitmqConfig.Topic_QUEUE_1,context);
//    }
//
//
//    public void topicSend2(){
//        String context = "hi, i am message 2";
//        amqpTemplate.convertAndSend(RabbitmqConfig.TopicExchange,RabbitmqConfig.Topic_QUEUE_2,context);
//    }
//
//
//}
