package com.lyoyang.lab.oauth.controller;

import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.enums.ResponseEnum;
import com.lyoyang.lab.common.exception.LabAuthException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/oauthTest")
    public String oauthTest() {
        return "oauthTest";
    }


    @GetMapping("/user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/signout")
    public ServiceResponse signout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        if (!consumerTokenServices.revokeToken(token)) {
            throw new LabAuthException(ResponseEnum.OPERATION_ERRO.getMessage());
        }
        return ServiceResponse.getSuccessInstance();
    }



}
