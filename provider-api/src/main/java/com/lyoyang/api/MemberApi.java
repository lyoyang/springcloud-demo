package com.lyoyang.api;

import com.lyoyang.dto.MemberRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: Brian
 * @Date: 2020/6/3 15:34
 * @Description:
 */
@FeignClient("member-provider")
public interface MemberApi {

    @RequestMapping(value = "/api/getMembers")
    List<MemberRespDto> getMembers();

}
