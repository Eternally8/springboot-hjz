package com.hjz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hjz.model.StudentVo;
import com.hjz.service.NacosProviderFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "sentinel使用示例")
@RestController
@RequestMapping("/limitReq")
public class SentinelController {

    @ApiOperation(value = "获取学生资源",notes = "这是一个限流的接口")
    @SentinelResource(blockHandler = "blockHandlerForGetUser")
    @GetMapping(value = "/getStu")
    public StudentVo getStu(){
        StudentVo vo = new StudentVo();
        vo.setAge(1);
        vo.setName("我是个限流资源");
        vo.setId(1);

        return vo;
    }


    // blockHandler 函数，原方法调用被限流/降级/系统保护的时候调用
    public StudentVo blockHandlerForGetUser(String id, BlockException ex) {
        return new StudentVo();
    }

}
