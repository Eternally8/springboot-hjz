package com.hjz.service;

import com.hjz.model.StudentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//path的设置server.context-path名称相对应
@FeignClient(value = "nacos-provider",path = "nacos-provider")
public interface NacosProviderFeignClient {

    @PostMapping("/nprovider/myHandle")
    StudentVo getData(@RequestBody StudentVo studentVo);

}
