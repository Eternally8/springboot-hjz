package com.jz.springMvc.servlet;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoController {
    String value() default "";
}
