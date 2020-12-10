package com.hjz.rabbitmq;

/**
 * 创建配置类：
 *  用来配置队列、交换器、路由等高级信息
 */

//@Slf4j
//@Configuration
//public class RabbitmqConfig {
//    @Resource
//    private RabbitTemplate rabbitTemplate;
//
//    //轮询消费模式
//    public static final String QUEUE_1 = "msg.queue";
//    public static final String QUEUE_2 = "msg.queue2";
//
//    public static final String ACK_QUEUE = "ack.queue";
//
//    //TOPIC模式
//    public static final String Topic_QUEUE_1 = "topic.queue";
//    public static final String Topic_QUEUE_2 = "topic.queue2";
//    //交换机
//    public static final String TopicExchange = "topic_exchange";
//
//
//    @PostConstruct
//    public RabbitTemplate rabbitTemplate(){
//        //消息发送失败返回到队列中,yml需要配置 publisher-returns : true
//        rabbitTemplate.setMandatory(true);  //Mandatory 强制的、托管的、命令的
//
//        //消息返回 yml需要配置 publisher-returns:true
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            String correlationId = message.getMessageProperties().getCorrelationId();  //correlation:相关的
//            log.info("消息：{}--发送失败,应答码：{}，原因：{}，交换机：{}，路由键：{}",
//                    correlationId,replyCode,replyText,exchange,routingKey);
//        });
//
//        //消息确认 yml需要配置publisher-confirms:true
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            if(ack){
////                log.info("消息发送到exchange成功，id:{}",correlationData.getId());
//            }else{
//                log.info("消息发送到exchange失败，id：{}，原因:{}",correlationData.getId(),cause);
//            }
//        });
//
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public Queue ackQueue(){
//        return new Queue(ACK_QUEUE);
//    }
//    @Bean
//    public Queue messageQueue(){
//        return new Queue(QUEUE_1);
//    }
//    @Bean
//    public Queue messageQueue2(){
//        return new Queue(QUEUE_2);
//    }
//    @Bean
//    public Queue topicQueue(){
//        return new Queue(Topic_QUEUE_1);
//    }
//    @Bean
//    public Queue topicQueue2(){
//        return new Queue(Topic_QUEUE_2);
//    }
//
//    /**
//     * 定义交换机
//     */
//    @Bean
//    public TopicExchange exchange(){
//        return new TopicExchange(TopicExchange);
//    }
//
//    /**
//     * 绑定消息队列到交换机,路由key:topic.message
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessage(Queue topicQueue, TopicExchange exchange) {
//        return BindingBuilder.bind(topicQueue).to(exchange).with("topic.queue");
//    }
//    @Bean
//    Binding bindingExchangeMessage2(Queue topicQueue, TopicExchange exchange) {
//        return BindingBuilder.bind(topicQueue).to(exchange).with("msg.queue");
//    }
//
//    /**
//     * “*”与“#”，用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）
//     * 绑定消息队列到交换机,路由key:topic.#
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessages(Queue topicQueue2, TopicExchange exchange){
//        return BindingBuilder.bind(topicQueue2).to(exchange).with("topic.#");
//    }
//
//
//
//}
