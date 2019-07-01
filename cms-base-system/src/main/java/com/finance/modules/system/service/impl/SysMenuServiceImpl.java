package com.finance.modules.system.service.impl;

import com.finance.modules.model.MenuModel;
import com.finance.modules.system.mapper.SysMenuMapper;
import com.finance.modules.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<MenuModel> getMenusByUserYun(String corpCode, String userCode) {
        return  sysMenuMapper.getMenusByUserYun(corpCode,userCode);
    }
}
