//package com.hjz.service;
//
//import com.hjz.model.StudentVo;
//import com.hjz.service.impl.NacosProviderFeignClientFallBack;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * path的设置server.context-path名称相对应(获取在path上直接写ip地址也是可行的)
// * 要使用Feign的fallback机制，需要开启Feign的Hystrix的功能，新增配置如下：
// * feign:
// *   hystrix:
// *     enabled: true
// */
//@FeignClient(value = "nacos-provider",path = "nacos-provider", fallback = NacosProviderFeignClientFallBack.class)
//public interface NacosProviderFeignClient {
//
//    @PostMapping("/nprovider/myHandle")
//    StudentVo getData(@RequestBody StudentVo studentVo);
//
//}
