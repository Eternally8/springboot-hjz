package com.hjz.rabbitmq;

//@Slf4j
//@Component
////@RabbitListener(queues = RabbitmqConfig.ACK_QUEUE)
//public class ReceiverAck {
//
//    @RabbitHandler
//    public void process(MqMsgVo mqMsgVo, Channel channel, Message message) throws IOException {
//        log.info("ReceiverAck收到消息: {}", JSON.toJSONString(mqMsgVo));
//        try {
//            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
//            /**
//             * //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
//             * channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//             *
//             * //ack返回false，并重新回到队列，api里面解释得很清楚
//             * channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//             *
//             * //拒绝消息
//             * channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
//             *
//             * 参考链接   https://blog.csdn.net/linpeng_1/article/details/80505828
//             */
//
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//            log.info("ReceiverAck_success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            //丢弃这条消息
//            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
//            log.info("ReceiverAck_fail");
//        }
//
//    }
//
//}
