package com.hjz.starter;

import lombok.Data;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/6/18 11:46
 */
public class HelloService {
    private String msg;
    private String name;

    public void say() {
        System.out.println(name + ":" + msg);
    }

}
