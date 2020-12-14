package com.hjz.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2020/12/14 20:43
 */
@Configuration
public class BalanceConfig {
    /**
     * 默认按顺序轮询
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}