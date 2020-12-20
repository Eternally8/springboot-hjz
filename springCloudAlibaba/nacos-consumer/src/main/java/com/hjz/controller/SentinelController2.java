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
//@RequestMapping("/limitReq2")
//public class SentinelController2 {
//
////    @ApiOperation(value = "获取学生资源",notes = "这是一个限流的接口")
////    @SentinelResource(value = "getStu",blockHandler = "blockHandlerForGetUser")
////    @GetMapping(value = "/getStu")
////    public StudentVo getStu(){
////        log.info("调用获取资源");
////        StudentVo vo = new StudentVo();
////        vo.setAge(1);
////        vo.setName("获取到一个限流资源");
////        vo.setId(1);
////
////        return vo;
////    }
////
////
////    @PostConstruct
////    public void initFlowRules(){
////        List<FlowRule> rules = new ArrayList<>();
////        FlowRule rule = new FlowRule();
////        //资源名，资源名是限流规则的作用对象
////        rule.setResource("getStu");
////        //限流阈值类型，QPS 模式（1）或并发线程数模式（0）
////        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
////        //限流阈值
////        rule.setCount(1);
////        rules.add(rule);
////        FlowRuleManager.loadRules(rules);
////    }
////
////
////    // blockHandler 函数，原方法调用被限流/降级/系统保护的时候调用
////    public StudentVo blockHandlerForGetUser(BlockException ex) {
////        StudentVo v = new StudentVo();
////        v.setAge(0);
////        v.setName("没有访问到具体资源");
////        v.setId(0);
////        return v;
////    }
//
//}
