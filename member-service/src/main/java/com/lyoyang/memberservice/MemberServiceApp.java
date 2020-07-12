package com.lyoyang.memberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.lyoyang.api"})
@EnableDiscoveryClient
@EnableHystrix
public class MemberServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApp.class, args);
	}
}
