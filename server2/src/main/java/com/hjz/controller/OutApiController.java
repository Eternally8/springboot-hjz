package com.hjz.controller;

import com.alibaba.fastjson.JSON;
import com.hjz.model.StudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "对外接口")
@RestController
@RequestMapping("/out")
public class OutApiController {

    @ApiOperation(value = "测试对外接口使用",notes = "测试对外接口使用")
    @PostMapping(value = "/testApi")
    public StudentVo testApi(@RequestBody StudentVo studentVo){
        log.info("server2获取了参数:{}", JSON.toJSONString(studentVo));

        studentVo.setAge(studentVo.getAge() + 1);
        return studentVo;
    }


}
