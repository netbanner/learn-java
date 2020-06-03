package com.example.distributedlock.redissionlock;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuwh
 * @date 2018/12/12 15:51
 * @desc
 */
@Configuration
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
