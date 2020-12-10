package com.hjz.example.aop.DeclareParents;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
    //"+"表示Men的所有子类(2.0以后版本+不能用了)；defaultImpl 表示默认需要添加的新的类
    @DeclareParents(value = "com.hjz.example.aop.DeclareParents.Women", defaultImpl = FemaleAnimal2.class)
    public Animal animal;

}