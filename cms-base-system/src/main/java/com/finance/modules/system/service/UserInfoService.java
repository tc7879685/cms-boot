package com.finance.modules.system.service;

import com.finance.modules.system.entity.UserInfo;

public interface UserInfoService  {

    /**
     * 根据corpCode 和UserName 查询用户信息
     * @param corpCode
     * @param userName
     * @return
     */
    UserInfo getUserInfoByName(String corpCode,String userName);

}
