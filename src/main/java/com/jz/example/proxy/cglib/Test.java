package com.jz.example.proxy.cglib;

import com.jz.example.proxy.JdkDong.LiuDeHua;
import com.jz.example.proxy.JdkDong.Star;

public class Test {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Star star2 = (Star)proxy.CreatProxyedObj(LiuDeHua.class);

        System.out.println(star2.dance("lalal"));
    }

}
