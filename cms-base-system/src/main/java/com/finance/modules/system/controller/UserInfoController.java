package com.finance.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finance.common.aspect.annotation.AutoLog;
import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.util.PasswordUtil;
import com.finance.common.vo.Result;
import com.finance.model.TableModel;
import com.finance.modules.model.UserModel;
import com.finance.modules.system.entity.DepositInfo;
import com.finance.modules.system.entity.UnitInfo;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.service.IUnitInfoService;
import com.finance.modules.system.service.IUserInfoService;
import com.finance.modules.system.util.MaxIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;

@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    IUnitInfoService unitInfoService;
    @Autowired
    MaxIDUtil maxIDUtil;
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


    /**
     *   添加
     * @param userInfo
     * @return
     */
    @AutoLog(value = "用户维护-添加")
    @PostMapping(value = "/add")
    public Result<UserInfo> add(UserInfo userInfo,@RequestParam(name="unitUUID")String unitUUID, HttpServletRequest req) {
        Result<UserInfo> result = new Result<UserInfo>();
        UserBean userBean = this.getUserBean(req);
        String password = PasswordUtil.encrypt(userInfo.getUserCode(),"1");
        userInfo.setCorpCode(userBean.getCorpCode());
        userInfo.setUserPassword(password);
        UnitInfo unitInfo = unitInfoService.getById(unitUUID);
        userInfo.setUnitCode(unitInfo.getUnitCode());
        Integer userUUID = maxIDUtil.getMaxId(new TableModel("sys_user_info","userUUID"));
        userInfo.setUserUUID(userUUID);
        userInfo.setAttempts(0);//连续输入错误密码次数
        try {
            userInfoService.save(userInfo);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
        }
        return result;
    }

}
