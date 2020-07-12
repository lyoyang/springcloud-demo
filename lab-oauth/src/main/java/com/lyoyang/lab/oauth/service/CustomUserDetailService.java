package com.lyoyang.lab.oauth.service;

import com.lyoyang.lab.common.entity.SimpleUserDetail;
import com.lyoyang.lab.oauth.entity.LabUser;
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
    private LabUserService labUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LabUser systemUser = labUserService.findByName(username);
        if (systemUser != null) {
            String userPermissions = labUserService.findUserPermissions(systemUser.getUsername());
            SimpleUserDetail simpleUserDetail = new SimpleUserDetail();
            simpleUserDetail.setUsername(systemUser.getUsername());
            simpleUserDetail.setPassword(systemUser.getPassword());
            simpleUserDetail.setEnable(true);
            simpleUserDetail.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(userPermissions));
            return simpleUserDetail;
        } else {
            throw new UsernameNotFoundException("");
        }
    }

}
