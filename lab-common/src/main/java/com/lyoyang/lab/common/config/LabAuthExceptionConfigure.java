package com.lyoyang.lab.common.config;

import com.lyoyang.lab.common.handler.LabAccessDeniedHandler;
import com.lyoyang.lab.common.handler.LabAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class LabAuthExceptionConfigure {


    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public LabAccessDeniedHandler accessDeniedHandler() {
        return new LabAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public LabAuthExceptionEntryPoint authenticationEntryPoint() {
        return new LabAuthExceptionEntryPoint();
    }
}
