package com.finance.modules.system.service.impl;

import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.mapper.UserInfoMapper;
import com.finance.modules.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl  implements UserInfoService {

    @Autowired
    private UserInfoMapper userMapper;

    @Override
    public UserInfo getUserInfoByName(String corpCode, String userName) {
        return  userMapper.getUserByName(userName,corpCode);
    }
}
