package com.lyoyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: yangbing
 * @Date: 2019/7/22 15:53
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.lyoyang"})
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
