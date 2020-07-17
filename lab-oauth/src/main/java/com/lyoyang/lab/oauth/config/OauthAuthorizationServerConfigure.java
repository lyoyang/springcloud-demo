package com.lyoyang.lab.oauth.config;

import com.lyoyang.lab.common.handler.LabAccessDeniedHandler;
import com.lyoyang.lab.common.handler.LabAuthExceptionEntryPoint;
import com.lyoyang.lab.oauth.properties.LabAuthProperties;
import com.lyoyang.lab.oauth.properties.LabClientsProperties;
import com.lyoyang.lab.oauth.service.CustomUserDetailService;
import com.lyoyang.lab.oauth.translator.LabWebResponseExceptionTranslator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class OauthAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private LabAuthProperties labAuthProperties;


    @Autowired
    private LabWebResponseExceptionTranslator labWebResponseExceptionTranslator;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        LabClientsProperties[] clientsConfigs = labAuthProperties.getClients();

        InMemoryClientDetailsServiceBuilder inMemoryBuilder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(clientsConfigs)) {
            for (LabClientsProperties clientsProperties : clientsConfigs) {
                if (StringUtils.isEmpty(clientsProperties.getClient())) {
                    throw new RuntimeException("client不能为空");
                }
                if (StringUtils.isEmpty(clientsProperties.getSecret())) {
                    throw new RuntimeException("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(clientsProperties.getGrantType(), ",");
                inMemoryBuilder.withClient(clientsProperties.getClient())
                        .secret(passwordEncoder.encode(clientsProperties.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(clientsProperties.getScope())
                        .accessTokenValiditySeconds(labAuthProperties.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(labAuthProperties.getRefreshTokenValiditySeconds());
            }
        }
    }

    @Override
    @SuppressWarnings("all")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(customUserDetailService)
                .authenticationManager(authenticationManager)
//                .tokenServices(tokenService())
                .exceptionTranslator(labWebResponseExceptionTranslator);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(labAuthProperties.getAccessTokenValiditySeconds());
        tokenServices.setRefreshTokenValiditySeconds(labAuthProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(customUserDetailService);
        accessTokenConverter.setUserTokenConverter(defaultUserAuthenticationConverter);
        jwtAccessTokenConverter.setSigningKey("lab");
        return jwtAccessTokenConverter;
    }






}
