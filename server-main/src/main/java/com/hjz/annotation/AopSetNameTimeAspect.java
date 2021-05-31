package com.hjz.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class AopSetNameTimeAspect {
    /**
     * Spring中使用@Pointcut注解来定义方法切入点
     * @Pointcut 用来定义切点，针对方法  @Aspect 用来定义切面，针对类
     * 后面的增强均是围绕此切入点来完成的
     * 此处仅配置被我们刚才定义的注解：AuthToken修饰的方法即可
     */
    @Pointcut("@annotation(aopSetNameTime)")
    public void doAop(AopSetNameTime aopSetNameTime) {
    }


    @Before("doAop(AopSetNameTime)")   // 表演之前
    public void t1(JoinPoint point){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用前连接点方法为：" + methodName + ",参数为：" + args);
    }

    @AfterReturning(returning="rvt", value="doAop(AopSetNameTime)")
    public String handleAop(Object rvt) {
        System.out.println("获取目标方法返回值:" + rvt.toString());
        return "注解处理类返回";
    }

}
