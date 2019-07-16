package com.finance.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.modules.system.entity.RoleInfo;
import com.finance.modules.system.mapper.RoleInfoMapper;
import com.finance.modules.system.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper,RoleInfo> implements IRoleInfoService {

    @Autowired
    private RoleInfoMapper sysRoleMapper;

    @Override
    public List<Map> getRoleList(RoleInfo roleInfo) {


        return sysRoleMapper.getRoleList(roleInfo);
    }

    @Override
    public RoleInfo queryParentRole(String corpCode, String id) {
        return  sysRoleMapper.queryParentRole(corpCode,id);
    }

    @Override
    public List<Map> queryAllMenusByCorpCode(String corpCode) {
        return sysRoleMapper.queryAllMenusByCorpCode(corpCode);
    }

    @Override
    public List<String> queryCurrentMenu(String corpCode, Integer roleUUID) {
        return sysRoleMapper.queryCurrentMenu(corpCode,roleUUID);
    }


}
