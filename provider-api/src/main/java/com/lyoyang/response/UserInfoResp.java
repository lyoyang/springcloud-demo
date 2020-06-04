package com.lyoyang.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: yangbing
 * @Date: 2019/7/22 15:23
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResp implements Serializable {

    private Long id;

    private String userName;

    private Integer age;

    private String phone;

    private String email;
}
