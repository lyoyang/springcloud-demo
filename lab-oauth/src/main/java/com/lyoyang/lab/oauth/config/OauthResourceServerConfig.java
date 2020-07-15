package com.lyoyang.lab.oauth.config;

import com.lyoyang.lab.common.handler.LabAccessDeniedHandler;
import com.lyoyang.lab.common.handler.LabAuthExceptionEntryPoint;
import com.lyoyang.lab.oauth.properties.LabAuthProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OauthResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private LabAccessDeniedHandler labAccessDeniedHandler;

    @Autowired
    private LabAuthExceptionEntryPoint labAuthExceptionEntryPoint;

    @Autowired
    private LabAuthProperties labAuthProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(labAuthProperties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(labAuthExceptionEntryPoint)
                .accessDeniedHandler(labAccessDeniedHandler);
    }
}
