package com.finance.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.vo.Result;
import com.finance.modules.model.UserModel;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 查询用户列表
     * @param user
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<UserModel>> queryPageList(UserInfo user, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<UserModel>> result = new Result<IPage<UserModel>>();
        UserBean userBean =  super.getUserBean(req);
        user.setCorpCode(userBean.getCorpCode());
        Page<UserModel> pageList = new Page<UserModel>(pageNo, pageSize);
        pageList = userInfoService.getUserList(pageList,user);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }
}
