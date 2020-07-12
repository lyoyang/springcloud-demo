package com.lyoyang.lab.oauth.service;

import com.lyoyang.lab.common.entity.SimpleUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SimpleUserDetail simpleUserDetail = new SimpleUserDetail();
        simpleUserDetail.setUsername(username);
        simpleUserDetail.setPassword(passwordEncoder.encode("123456"));
        simpleUserDetail.setEnable(true);
        simpleUserDetail.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
        return simpleUserDetail;
    }
}
