package com.hjz.service.otherServer;

import com.hjz.model.StudentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "server2",path = "server2")
public interface Server2FeignClient2 {

    @PostMapping("/out/testApi")
    StudentVo getData(@RequestBody StudentVo studentVo);

}
