package com.finance.modules.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.model.TreeModel;
import com.finance.modules.model.MenuModel;
import com.finance.modules.system.entity.MenuInfo;
import com.finance.modules.system.entity.RoleInfo;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IRoleInfoService extends IService<RoleInfo> {


    /**
     * 查询角色列表
     * @param roleInfo
     * @return
     */
    List<Map> getRoleList(RoleInfo roleInfo);

    /**
     * 根据当前角色ID查询上级角色
     * @param corpCode
     * @param id
     * @return
     */
    RoleInfo queryParentRole(String corpCode,String id);


    /**
     * 查询机构下全全部菜单
     * @param corpCode
     * @return
     */
    List<Map> queryAllMenusByCorpCode(String corpCode);

    /**
     * 查询当前角色下所有的菜单权限id
     * @param corpCode
     * @param roleUUID
     * @return
     */
    List<String> queryCurrentMenu(String corpCode,Integer roleUUID);


}
