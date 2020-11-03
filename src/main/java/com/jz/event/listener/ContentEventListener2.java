package com.jz.event.listener;

import com.alibaba.fastjson.JSON;
import com.jz.event.ContentEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContentEventListener2 implements ApplicationListener<ContentEvent> {

    @Override
    public void onApplicationEvent(ContentEvent event) {
        log.info("ContentEventListener2收到信息--:{},event_thread:{}", JSON.toJSONString(event.getSource()),Thread.currentThread().getName());
    }
}
