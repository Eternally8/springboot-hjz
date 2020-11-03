package com.jz.service;

import com.jz.utils.OtherUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OtherService {

    @Autowired
    private OtherUtils otherUtils;

    public String getTime() {
        return otherUtils.getTime();
    }

    @Async(value = "taskExecutor")
    public void getThreadName() {
        System.out.println("asdfasdfasd1111111" + Thread.currentThread().getName());
    }

    @Async
    public void getThreadName2() {
        System.out.println("asdfasdfasd2222" + Thread.currentThread().getName());
    }
}
