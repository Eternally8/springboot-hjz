package com.hjz.service.otherServer;

import com.hjz.model.StudentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="service2",url = "http://localhost:28082/server2")
public interface Server2FeignClient {

    @PostMapping("/out/testApi")
    StudentVo getData(@RequestBody StudentVo studentVo);

}
