package com.hjz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NproviderApplication.class, args);
    }

}