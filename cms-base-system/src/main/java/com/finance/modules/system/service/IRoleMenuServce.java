package com.finance.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.modules.system.entity.RoleMenu;

public interface IRoleMenuServce  extends IService<RoleMenu> {


    /**
     * 保存菜单
     * @param corpCode
     * @param roleCode
     * @param permissionIds  之前选择的菜单
     * @param lastPermissionIds //选中需要保存的菜单
     */
    void  saveRoleMenu(String corpCode,String roleCode,String permissionIds,String lastPermissionIds);
}
