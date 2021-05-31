package com.hjz.service;

import cn.hutool.core.date.SystemClock;
import com.hjz.annotation.AopSetNameTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/5/31 15:37
 */
@Slf4j
@Service
public class AopService {

    @AopSetNameTime
    public String getValue(String name) {
        log.info("aaa");
        return name + "" + SystemClock.now();
    }


}
