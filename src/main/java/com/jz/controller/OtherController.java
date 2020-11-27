package com.jz.controller;

import com.jz.service.OtherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Api(tags = "其他方法测试")
@RestController
@RequestMapping("/otherController")
public class OtherController {
    @Autowired
    private OtherService otherService;

    @ApiOperation(value = "测试注解异步线程执行")
    @GetMapping("test")
    public String test() {
        /**
         * 设置子线程共享一个
         *  @Autowired
         *  private HttpServletRequest req;
         */

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(servletRequestAttributes,true);

        for (int i=0;i<10;i++) {
            Thread t = new Thread();
            t.run();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    System.out.println(otherService.getTime());
                }
            };

            r.run();
        }

        otherService.getThreadName();
        otherService.getThreadName2();

        return "suceess";
    }

    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
    }

}
