//package com.hjz.controller;
//
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@Api(tags = "sentinel使用示例")
//@RestController
//@RequestMapping("/limitReq")
//public class SentinelController {
//
////    @ApiOperation(value = "测试限流")
////    @GetMapping("/hello")
////    public String hello(){
////        try(Entry entry = SphU.entry("Hello")) {
////            return "Hello Sentinel";
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return "系统繁忙，请稍后";
////    }
////
////    /** 定义限流规则
////     * @PostConstruct 此注解的含义是：本类构造方法执行结束后执行
////     */
////
////    @PostConstruct
////    public void init(){
////        //1.创建存放限流规则的集合
////        List<FlowRule> rules = new ArrayList<>();
////        //2.创建限流规则
////        FlowRule rule = new FlowRule();
////        //定义资源，表示Sentinel会对哪个资源生效
////        rule.setResource("Hello");
////        //定义限流的类型(此处使用QPS作为限流类型)
////        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
////        //定义QPS每秒通过的请求数
////        rule.setCount(1);
////        //3.将限流规则存放到集合中
////        rules.add(rule);
////        //4.加载限流规则
////        FlowRuleManager.loadRules(rules);
////    }
//
//
//}
