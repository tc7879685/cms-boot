package com.finance.shiro.web;

import com.finance.common.system.util.JwtUtil;
import com.finance.common.util.PasswordUtil;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.service.IUserInfoService;
import com.finance.shiro.vo.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestWebController {
 
    private IUserInfoService userService;
 
    @Autowired
    public void setService(IUserInfoService userService) {
        this.userService = userService;
    }
 
    @PostMapping("/sys/login1")
    public ResponseBean login(@RequestParam("corpCode") String corpCode,
                              @RequestParam("userName") String userName,
                              @RequestParam("password") String password) {
    	UserInfo user = userService.getUserInfoByName(corpCode,userName);
    	if(user==null) {
    		return new ResponseBean(200, "用户不存在！", JwtUtil.sign(corpCode,userName, user.getUserPassword()));
    	}
    	String passwordEncode = PasswordUtil.encrypt(userName, password);
        if (passwordEncode.equals(user.getUserPassword())){
            return new ResponseBean(200, "Login success", JwtUtil.sign(corpCode,userName, user.getUserPassword()));
        } else {
            throw new UnauthorizedException();
        }
    }
 
    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }
 
    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }
 
    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }
 
    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }
 
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}