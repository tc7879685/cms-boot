package com.finance.common.system.controller;

import com.finance.common.constant.DefContants;
import com.finance.common.system.model.UserBean;
import com.finance.common.system.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

public class BaseController {


    /**
     * @param request
     * @return
     */
    public UserBean getUserBean(HttpServletRequest request){
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        String[] usernameAyyay = JwtUtil.getUsername(token);
        UserBean userBean = new UserBean();
        userBean.setCorpCode(usernameAyyay[0]);
        userBean.setUserCode(usernameAyyay[1]);
        return  userBean;
    }

}
