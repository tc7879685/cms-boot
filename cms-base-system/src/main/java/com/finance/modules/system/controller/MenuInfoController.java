package com.finance.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.finance.common.system.util.JwtUtil;
import com.finance.common.util.oConvertUtils;
import com.finance.common.vo.Result;
import com.finance.modules.model.MenuModel;
import com.finance.modules.system.service.IMenuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 */
@RestController
@RequestMapping("/sys/menu")
@Slf4j
public class MenuInfoController {

    @Autowired
    IMenuInfoService sysMenuService;

    /**
     * 通过token获取用户菜单
     * @param token
     * @return
     */
    @GetMapping("/getUserPermissionByToken")
    public Result<?> getUserMenuByToken(@RequestParam(name = "token",required = true) String token){
        Result<JSONObject> result = new Result<JSONObject>();
        try {
            if (oConvertUtils.isEmpty(token)) {
                return Result.error("TOKEN不允许为空！");
            }
            log.info(" ------ 通过令牌获取用户拥有的访问菜单 ---- TOKEN ------ " + token);
            String[] usernameAyyay = JwtUtil.getUsername(token);
            String corpCode = usernameAyyay[0];
            String userCode = usernameAyyay[1];
            List<MenuModel> metaList = sysMenuService.getMenusByUserYun(corpCode,userCode);
            JSONObject json = new JSONObject();
            //查询所有的权限
            json.put("menu", metaList);
            result.setResult(json);
            result.success("查询成功");
        } catch (Exception e) {
            result.error500("查询失败:" + e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
