package com.lyoyang.lab.oauth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyoyang.lab.oauth.entity.LabMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author brian
 * @since 2020-07-12
 */
public interface LabMenuMapper extends BaseMapper<LabMenu> {

    List<LabMenu> findUserPermission(String username);

}
