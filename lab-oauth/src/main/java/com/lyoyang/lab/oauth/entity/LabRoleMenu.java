package com.lyoyang.lab.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author brian
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LabRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ROLE_ID")
    private Long roleId;

    @TableField("MENU_ID")
    private Long menuId;


}
