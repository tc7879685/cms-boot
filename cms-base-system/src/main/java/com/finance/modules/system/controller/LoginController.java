package com.finance.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.finance.common.constant.CommonConstant;
import com.finance.common.system.util.JwtUtil;
import com.finance.common.util.PasswordUtil;
import com.finance.common.util.RedisUtil;
import com.finance.common.vo.Result;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.service.UserInfoService;
import com.finance.shiro.vo.DefContants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     * @param corpCode
     * @param userCode
     * @param userPassword
     * @return
     */
    @PostMapping("/login")
    public Result<JSONObject> login(@RequestParam("corpCode") String corpCode,
                                    @RequestParam("userCode") String userCode,
                                    @RequestParam("userPassword") String userPassword){
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject object = new JSONObject();
        UserInfo user =  userInfoService.getUserInfoByName(corpCode,userCode);
        if(user == null){
            result.error500("该用户不存在");
            return  result;
        }else{
            //验证密码
            String password =  PasswordUtil.encrypt(userCode,userPassword);
            if(!user.getUserPassword().equals(password)){
                result.error500("用户名或密码错误");
                return result;
            }
        }
        //登录成功,生成token
        String token = JwtUtil.sign(corpCode,userCode,userPassword);
        //设置token
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN+token,token);
        //设置超时时间
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN+token, JwtUtil.EXPIRE_TIME/1000);
        object.put("token",token);

        result.setResult(object);
        result.success("登录成功");
    return  result;

    }


    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout")
    public Result<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        //用户退出逻辑
        Subject subject = SecurityUtils.getSubject();
        UserInfo sysUser = (UserInfo)subject.getPrincipal();
       // sysBaseAPI.addLog("用户名: "+sysUser.getUserCode()+",退出成功！", CommonConstant.LOG_TYPE_1, null);
        log.info(" 用户名:  "+sysUser.getUserCode()+",退出成功！ ");
        subject.logout();
        //获取token
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        //清空用户Token缓存
        redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
        //清空用户权限缓存：权限Perms和角色集合
        //redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_ROLE + sysUser.getUsername());
       // redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_PERMISSION + sysUser.getUsername());
        return Result.ok("退出登录成功！");
    }


}
