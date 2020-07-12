package com.lyoyang.lab.oauth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyoyang.lab.oauth.entity.LabUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author brian
 * @since 2020-07-12
 */
public interface LabUserMapper extends BaseMapper<LabUser> {
    LabUser findByName(String username);
}
