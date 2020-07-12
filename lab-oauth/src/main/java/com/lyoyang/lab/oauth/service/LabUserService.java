package com.lyoyang.lab.oauth.service;

import com.lyoyang.lab.oauth.entity.LabMenu;
import com.lyoyang.lab.oauth.entity.LabUser;
import com.lyoyang.lab.oauth.mapper.LabMenuMapper;
import com.lyoyang.lab.oauth.mapper.LabUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabUserService {

    @Autowired
    private LabUserMapper labUserMapper;

    @Autowired
    private LabMenuMapper labMenuMapper;

    public LabUser findByName(String username) {
        return labUserMapper.findByName(username);
    }


    public String findUserPermissions(String username) {
        List<LabMenu> userPermission = labMenuMapper.findUserPermission(username);
        return userPermission.stream().map(LabMenu::getPerms).collect(Collectors.joining(","));
    }




}
