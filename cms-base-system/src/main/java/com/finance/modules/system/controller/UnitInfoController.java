package com.finance.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.util.TreeUtil;
import com.finance.common.vo.Result;
import com.finance.modules.model.UserModel;
import com.finance.modules.system.entity.UnitInfo;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.service.IUnitInfoService;
import com.finance.modules.system.util.TreeBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/unit")
@Slf4j
public class UnitInfoController extends BaseController {

    @Autowired
    IUnitInfoService unitInfoService;

    /**
     * 查询用户列表
     * @param unitInfo
     * @param req
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Map> queryPageList(UnitInfo unitInfo, HttpServletRequest req) {
        Result<Map> result = new Result<Map>();
        UserBean userBean =  super.getUserBean(req);
        unitInfo.setCorpCode(userBean.getCorpCode());
        List<Map> list =  unitInfoService.queryUnitList(unitInfo);
        List<String> ids = new ArrayList<String>();
        for(Map map:list){
            ids.add((String) map.get("unitCode"));
        }
        Map resMap = new HashMap();
        resMap.put("ids",ids);
        resMap.put("treeData",TreeBeanUtil.getTreeUnitList(list));
        result.setSuccess(true);
        result.setResult(resMap);
        return result;
    }
}
