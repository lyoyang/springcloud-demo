package com.lyoyang.lab.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:lab-gateway.properties"})
@ConfigurationProperties(prefix = "lab.gateway")
//@EnableConfigurationProperties(LabGatewayProperties.class)
public class LabGatewayProperties {

    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
