package com.lyoyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: yangbing
 * @Date: 2019/7/22 16:36
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.lyoyang"})
public class ConsumeApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeApplicationMain.class, args);
    }

}
