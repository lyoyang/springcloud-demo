package com.lyoyang.lab.server.system;

import com.lyoyang.lab.common.annotation.LabCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@LabCloudApplication
public class LabServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabServerSystemApplication.class, args);
    }

}
