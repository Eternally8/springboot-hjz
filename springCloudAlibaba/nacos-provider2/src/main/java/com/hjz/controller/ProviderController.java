package com.hjz.controller;

import com.alibaba.fastjson.JSON;
import com.hjz.model.StudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "nacosProvider提供接口")
@RestController
@RequestMapping("/nprovider")
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    @ApiOperation(value = "提供对外服务",notes = "测试提供对外服务")
    @PostMapping(value = "/myHandle")
    public StudentVo myHandle(@RequestBody StudentVo studentVo){
        log.info("np获取了参数:{}", JSON.toJSONString(studentVo));

        studentVo.setName(serverPort);
        studentVo.setAge(studentVo.getAge() + 1);
        return studentVo;
    }

}