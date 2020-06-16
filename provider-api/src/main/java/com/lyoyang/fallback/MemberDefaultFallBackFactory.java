package com.lyoyang.fallback;

import com.lyoyang.api.MemberApi;
import com.lyoyang.dto.MemberRespDto;
import feign.hystrix.FallbackFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author: Brian
 * @Date: 2020/6/16 11:34
 * @Description:
 */
public class MemberDefaultFallBackFactory implements FallbackFactory<MemberApi> {

    @Override
    public MemberApi create(Throwable throwable) {
        return new MemberApi() {
            @Override
            public List<MemberRespDto> getMembers() {
                return Collections.emptyList();
            }
        };
    }
}
