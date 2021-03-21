package com.hjz.service;

import com.alibaba.fastjson.JSON;
import com.jz.DemoService;
import com.jz.vo.StudentVo;
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
    public String sayHello(StudentVo vo) {
        return "我看到了一个学生:" + JSON.toJSONString(vo);
    }

}
