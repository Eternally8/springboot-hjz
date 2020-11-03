package com.jz.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.jz.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Objects;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2020/10/20 20:28
 */
@Slf4j
@Configuration
public class ApolloConfig {

//    Config appConfig = ConfigService.getConfig("liaoliao");
//
//    @ApolloConfigChangeListener({"liaoliao"})
//    private void acMysql(ConfigChangeEvent changeEvent) {
//        acMysqlHandle();
//    }
//
//    @PostConstruct
//    private void acMysqlHandle()  {
//        String key = appConfig.getProperty("redis_host", "no");
//        log.info("获取apollo的配置项值:{}",key);
//        try {
//            File yml = new File(Objects.requireNonNull(Application.class.getClassLoader().getResource("application.yml")).toURI());
//            YamlUtils.setYmlFile(yml);
//            YamlUtils.saveOrUpdateByKey("aaa",key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(1);
//    }

}
