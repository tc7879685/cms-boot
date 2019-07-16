package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色权限菜单表
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu {

    private String corpCode;

    private String roleCode;

    private String menuCode;
}
