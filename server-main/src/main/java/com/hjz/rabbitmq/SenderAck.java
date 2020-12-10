package com.hjz.rabbitmq;

//@Slf4j
//@Component
//public class SenderAck implements RabbitTemplate.ReturnCallback {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        log.info("return-msg：{},replyCode：{},replyText：{},exchange：{},routingKey：{}",
//                new String(message.getBody()),replyCode,replyText,exchange,routingKey);
//    }
//
//    public void ackSend(){
//        MqMsgVo mqMsgVo = new MqMsgVo(); //实现Serializable接口
//        mqMsgVo.setId(1101);
//        mqMsgVo.setName("lalalallala");
//        mqMsgVo.setCreateTimestamp(System.currentTimeMillis());
//
//        log.info("SenderAck发送消息:{},time:{}", JSON.toJSONString(mqMsgVo),new Date());
//
//        rabbitTemplate.setReturnCallback(this);
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            if (!ack) {
//                log.info("SenderAck消息发送失败,cause:{},correlationData:{}",cause,correlationData.toString());
//            } else {
//                log.info("SenderAck消息发送成功 ");
//            }
//        });
//        rabbitTemplate.convertAndSend(RabbitmqConfig.ACK_QUEUE, mqMsgVo);
//    }
//
//
//}
