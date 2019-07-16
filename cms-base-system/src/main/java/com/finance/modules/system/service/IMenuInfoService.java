package com.finance.modules.system.service;

import com.finance.modules.model.MenuModel;

import java.util.List;

public interface IMenuInfoService {



    /**
     * 通过用户查询菜单(云平台)（包含用户菜单和角色权限）
     * @param corpCode
     * @param userCode
     * @return
     */
    List<MenuModel> getMenusByUserYun(String corpCode, String userCode);




}
