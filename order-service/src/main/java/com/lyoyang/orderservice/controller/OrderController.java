package com.lyoyang.orderservice.controller;

import com.lyoyang.api.MemberApi;
import com.lyoyang.dto.MemberRespDto;
import com.lyoyang.orderservice.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private MemberApi memberApi;

    @Autowired
    private GitHubService gitHubService;

    @RequestMapping("/getMembers")
    public List<MemberRespDto> getAllUser() {
        return memberApi.getMembers();
    }

    @RequestMapping("/getGithubRepo")
    public ResponseEntity<byte[]> getGithubRepo(String queryStr) {
        return gitHubService.searchRepo(queryStr);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }



}
