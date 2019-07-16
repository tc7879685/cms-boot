package com.finance.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.modules.model.UserModel;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.mapper.UserInfoMapper;
import com.finance.modules.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userMapper;

    @Override
    public UserInfo getUserInfoByName(String corpCode, String userName) {
        return  userMapper.getUserByName(userName,corpCode);
    }


    @Override
    public Page<UserModel> getUserList(Page<UserModel> page, UserInfo userInfo) {
        return page.setRecords(userMapper.getUserList(page,userInfo));
    }
}
