package com.finance.modules.system.mapper;

import com.finance.modules.model.MenuModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuInfoMapper  {

    /**
     * 通过用户查询菜单(云平台)（包含用户菜单和角色权限）
     * @param corpCode
     * @param userCode
     * @return
     */
    List<MenuModel> getMenusByUserYun(@Param("corpCode")String corpCode, @Param("userCode")String userCode);

}
