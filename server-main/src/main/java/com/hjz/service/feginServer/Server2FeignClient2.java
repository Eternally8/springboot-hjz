package com.hjz.service.feginServer;

import com.hjz.model.StudentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//path的设置server.context-path名称相对应
@FeignClient(name = "server2",path = "server2")
public interface Server2FeignClient2 {

    @PostMapping("/out/testApi")
    StudentVo getData(@RequestBody StudentVo studentVo);

}
