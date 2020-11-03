package com.jz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "swagger示例区")
@RestController
@RequestMapping("/swg")
public class SwaggerController {
    /**
     * 无需校验,不加注解
     */
    @PostMapping("hello")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="用户ID", required=true),
            @ApiImplicitParam(name="name", value="名字", required=true, paramType="query", dataType="String", defaultValue="1"),
            @ApiImplicitParam(name="age", value="年龄", required=true)
    })
    public String hello(Integer id, String name, Integer age) {
        log.info("hello方法执行_id:{},name:{},age:{}",id,name,age);
        return "hi~ 我不需要用户权限";
    }



}
