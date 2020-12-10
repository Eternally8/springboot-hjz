package com.jz;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableApolloConfig
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
