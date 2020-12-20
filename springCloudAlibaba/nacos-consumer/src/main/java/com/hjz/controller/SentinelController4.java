package com.hjz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limitReq4")
public class SentinelController4 {

    @SentinelResource(value = "getStu",blockHandler = "exceptionHandler")
    @GetMapping(value = "/getStu")
    public String getStu(){
        return "123123123gghfgyhfg";
    }


    public String exceptionHandler(BlockException e){
        e.printStackTrace();
        return "系统繁忙请稍后再试";
    }

}
