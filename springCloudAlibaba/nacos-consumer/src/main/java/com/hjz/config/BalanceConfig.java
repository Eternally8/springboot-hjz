package com.hjz.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
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


    //配置类 将IRule 的实现类注册到spring容器中即可
    @Bean
    public IRule myRule(){
        // return new RoundRobinRule();//轮询
        // return new RetryRule();//重试
        // return new BestAvailableRule();
        // return new WeightedResponseTimeRule();// 权重
        return new RandomRule();
    }


}