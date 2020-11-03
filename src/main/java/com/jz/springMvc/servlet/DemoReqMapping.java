package com.jz.springMvc.servlet;

import java.lang.annotation.*;

//用在类和方法上
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoReqMapping {
    String value() default "";
}
