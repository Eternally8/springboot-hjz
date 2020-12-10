package com.hjz.servlet;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoAutowired {
    String value() default "";
}
