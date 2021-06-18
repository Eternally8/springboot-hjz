package com.hjz.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/6/18 11:42
 */
@ConfigurationProperties(prefix = "link")
@Data
public class HelloProperties {

    private String name;

    private String msg;

}
