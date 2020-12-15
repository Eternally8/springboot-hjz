package com.hjz.service.impl;

import com.hjz.model.StudentVo;
import com.hjz.service.NacosProviderFeignClient;
import org.springframework.stereotype.Component;

/**
 * Description： 对调用提供者接口报错异常处理
 * Author: hujingzheng
 * Date: 2020/12/15 10:58
 */
@Component
public class NacosProviderFeignClientFallBack implements NacosProviderFeignClient {
    @Override
    public StudentVo getData(StudentVo studentVo) {
        studentVo.setId(501);
        studentVo.setName("NacosProviderFeignClientFallBack_服务器繁忙!");
        return studentVo;
    }
}
