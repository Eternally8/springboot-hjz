package com.hjz;

import com.alibaba.fastjson.JSONArray;
import com.hjz.starter.HelloService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/6/18 12:01
 */
@SpringBootTest
public class starterTest {

    @Autowired
    private HelloService helloService;


    @Test
    public void test1() throws Exception {
        helloService.say();

        System.out.println();
    }


}
