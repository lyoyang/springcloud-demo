package com.lyoyang.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Brian
 * @Date: 2020/6/3 15:35
 * @Description:
 */

@Data
@Builder
public class MemberRespDto {

    private String id;

    private String name;

}
