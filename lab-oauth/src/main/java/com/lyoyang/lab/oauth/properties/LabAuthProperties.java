package com.lyoyang.lab.oauth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:lab-auth.properties"})
@ConfigurationProperties(prefix = "lab.auth")
@EnableConfigurationProperties(LabAuthProperties.class)
public class LabAuthProperties {

    private LabClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private LabValidateCodeProperties code = new LabValidateCodeProperties();




}

