package com.hjz.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "springCloud测试")
@RestController
@RequestMapping("/springcloud")
public class FeignController {
//    @Autowired
//    private Server2FeignClient2 server2FeignClient2;
//
//
//    @ApiOperation(value = "feign测试",notes = "发送一个请求")
//    @GetMapping(value = "/feignTest")
//    public StudentVo feignTest(){
//        StudentVo vo = new StudentVo();
//        vo.setAge(1);
//        vo.setName("lalalala");
//        vo.setId(1);
//
//        StudentVo result2 = server2FeignClient2.getData(vo);
//
//        return result2;
//    }


}
