package com.hjz.service;

import com.jz.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/3/6 16:03
 */
@Service
@DubboService(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "lalala:" + name;
    }

}
