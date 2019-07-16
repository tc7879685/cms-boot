package com.finance.modules.system.service.impl;

import com.finance.modules.model.MenuModel;
import com.finance.modules.system.mapper.MenuInfoMapper;
import com.finance.modules.system.service.IMenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuInfoServiceImpl implements IMenuInfoService {

    @Autowired
    MenuInfoMapper menuInfoMapper;

    @Override
    public List<MenuModel> getMenusByUserYun(String corpCode, String userCode) {
        return  menuInfoMapper.getMenusByUserYun(corpCode,userCode);
    }
}
