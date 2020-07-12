package com.lyoyang.lab.common.system.service.impl;

import com.lyoyang.lab.common.system.entity.LabUser;
import com.lyoyang.lab.common.system.mapper.LabUserMapper;
import com.lyoyang.lab.common.system.service.ILabUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author brian
 * @since 2020-07-12
 */
@Service
public class LabUserServiceImpl extends ServiceImpl<LabUserMapper, LabUser> implements ILabUserService {

}
