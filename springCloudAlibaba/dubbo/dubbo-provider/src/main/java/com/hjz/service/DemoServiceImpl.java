package com.hjz.service;

import com.jz.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/3/6 16:03
 */
@DubboService(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "lalala:" + name;
    }

}
