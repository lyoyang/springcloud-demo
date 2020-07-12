package com.lyoyang.lab.common.system.service.impl;

import com.lyoyang.lab.common.system.entity.LabUserRole;
import com.lyoyang.lab.common.system.mapper.LabUserRoleMapper;
import com.lyoyang.lab.common.system.service.ILabUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author brian
 * @since 2020-07-12
 */
@Service
public class LabUserRoleServiceImpl extends ServiceImpl<LabUserRoleMapper, LabUserRole> implements ILabUserRoleService {

}
