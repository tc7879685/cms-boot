package com.finance.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finance.model.TreeModel;
import com.finance.modules.model.MenuModel;
import com.finance.modules.system.entity.MenuInfo;
import com.finance.modules.system.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {


    List<Map> getRoleList(RoleInfo roleInfo);

    /**
     * 根据id查询上级角色
     * @param corpCode
     * @param id
     * @return
     */
    RoleInfo queryParentRole(@Param("corpCode") String corpCode, @Param("id") String id);

    /**
     * 查询机构下全全部菜单
     * @param corpCode
     * @return
     */
    List<Map> queryAllMenusByCorpCode(@Param("corpCode")String corpCode);

    /**
     * 查询当前角色下所有的菜单权限id
     * @param corpCode
     * @param roleUUID
     * @return
     */
    List<String> queryCurrentMenu(@Param("corpCode")String corpCode,@Param("roleUUID")Integer roleUUID);
}
