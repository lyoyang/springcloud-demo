package com.lyoyang.memberservice.controller;

import com.lyoyang.dto.MemberRespDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MemberController {

    @RequestMapping(value = "/api/getMembers")
    public List<MemberRespDto> getMembers() {
        MemberRespDto m1 = MemberRespDto.builder().id("1").name("jim").build();
        MemberRespDto m2 = MemberRespDto.builder().id("2").name("alice").build();
        MemberRespDto m3 = MemberRespDto.builder().id("3").name("bob").build();
        return Arrays.asList(m1, m2, m3);
    }

}
