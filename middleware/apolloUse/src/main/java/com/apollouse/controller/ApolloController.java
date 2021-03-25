package com.apollouse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "apollo模块")
@RestController
@RequestMapping("/apolloController")
public class ApolloController {

    /**
     *   测试apollo的获取配置项的值(先启动apollo服务器端,示例是demo.sh(要修改配置其DB配置),然后访问http://localhost:8070/
     *   再配置一个应用，注意APPID要对应上
     *   详见链接https://github.com/ctripcorp/apollo/wiki/Quick-Start
     */
    @Value("${testApolloValue}")
    String testApolloValue;

    @ApiOperation(value = "获取apollo配置项的值")
    @GetMapping("/getApolloValue")
    public String getApolloValue() {
        log.info("apollo配置项的值:{}",testApolloValue);
        return testApolloValue;
    }

}
