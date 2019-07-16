package com.finance.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.modules.model.UserModel;
import com.finance.modules.system.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 根据corpCode 和UserName 查询用户信息
     * @param corpCode
     * @param userName
     * @return
     */
    UserInfo getUserInfoByName(String corpCode,String userName);

    /**
     * 查询用户列表
     * @param page
     * @param userInfo
     * @return
     */
    Page<UserModel> getUserList(Page<UserModel> page, UserInfo userInfo);

}
