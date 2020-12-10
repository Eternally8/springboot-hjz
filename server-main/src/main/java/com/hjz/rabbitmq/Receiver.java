package com.hjz.rabbitmq;

//@Slf4j
//@Component
//public class Receiver {
//
//    /**
//     * 轮询消费模式
//     *  简单的消息推送,如果是多个方法一起去监听，测试结果显示是轮询来监听处理消息对列中的消息
//     *  多个消费者可以订阅同一个Queue，这时Queue中的消息会被平均分摊给多个消费者进行处理，
//     *  而不是每个消费者都收到所有的消息并处理。
//     */
//    @RabbitListener(queues = RabbitmqConfig.QUEUE_1)
//    public void process(String Str) {
//        log.info("接收到消息:{},time:{}",Str,new Date());
//    }
//
//    @RabbitListener(queues = RabbitmqConfig.QUEUE_1)
//    public void process11(String Str) {
//        log.info("接收到消息11:{},time:{}",Str,new Date());
//    }
//
//    @RabbitListener(queues = RabbitmqConfig.QUEUE_1)
//    public void process12(String Str) {
//        log.info("接收到消息12:{},time:{}",Str,new Date());
//    }
//
//    //接受一个对象也可以,上面的String和AmqEvent都是对象类型
//    @RabbitListener(queues = RabbitmqConfig.QUEUE_2)
//    public void process2(MqMsgVo Str) {
//        log.info("接收到消息2:{},time:{}", JSON.toJSONString(Str),new Date());
//    }
//
//
//    //TOPIC模式的信息接受
//    @RabbitListener(queues = RabbitmqConfig.Topic_QUEUE_1)
//    public void process3(String s) {
//        log.info("接收到消息3:{},time:{}", JSON.toJSONString(s),new Date());
//    }
//
//    @RabbitListener(queues = RabbitmqConfig.Topic_QUEUE_2)
//    public void process31(String s) {
//        log.info("接收到消息31:{},time:{}", JSON.toJSONString(s),new Date());
//    }
//
//}
