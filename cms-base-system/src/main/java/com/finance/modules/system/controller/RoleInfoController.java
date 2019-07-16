package com.finance.modules.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.system.query.QueryGenerator;
import com.finance.common.util.TreeUtil;
import com.finance.common.vo.Result;
import com.finance.modules.system.entity.RoleInfo;
import com.finance.modules.system.service.IRoleInfoService;
import com.finance.modules.system.service.IRoleMenuServce;
import com.finance.modules.system.util.TreeBeanUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@RestController
@RequestMapping("/sys/role")
@Slf4j
public class RoleInfoController extends BaseController {
	@Autowired
	private IRoleInfoService sysRoleService;
	@Autowired
	private IRoleMenuServce roleMenuServce;


	/**
	  * 分页列表查询
	 * @param roleInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	/*@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<IPage<RoleInfo>> queryPageList(RoleInfo roleInfo,
												@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												HttpServletRequest req) {
		UserBean userBean =  super.getUserBean(req);
		roleInfo.setCorpCode(userBean.getCorpCode());
		Result<IPage<RoleInfo>> result = new Result<IPage<RoleInfo>>();
		QueryWrapper<RoleInfo> queryWrapper = QueryGenerator.initQueryWrapper(roleInfo, req.getParameterMap());
		Page<RoleInfo> page = new Page<RoleInfo>(pageNo, pageSize);
		IPage<RoleInfo> pageList = sysRoleService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}*/

	/**
	 * 分页列表查询
	 * @param roleInfo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryRole", method = RequestMethod.GET)
	public Result<List<Map>> queryRole(RoleInfo roleInfo,HttpServletRequest req) {
		UserBean userBean =  super.getUserBean(req);
		roleInfo.setCorpCode(userBean.getCorpCode());
		Result<List<Map>> result = new Result<List<Map>>();
		List<Map> pageList = sysRoleService.getRoleList(roleInfo);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}


	/**
	 * 分页列表查询
	 * @param roleInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Map> queryPageList(RoleInfo roleInfo,
												 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												 HttpServletRequest req) {
		UserBean userBean =  super.getUserBean(req);
		roleInfo.setCorpCode(userBean.getCorpCode());
		Result<Map> result = new Result<Map>();
		QueryWrapper<RoleInfo> queryWrapper = QueryGenerator.initQueryWrapper(roleInfo, req.getParameterMap());
		Page<RoleInfo> page = new Page<RoleInfo>(pageNo, pageSize);
		List<Map> pageList = sysRoleService.getRoleList(roleInfo);
		List ids = new ArrayList<>();
		for (Map map:pageList){
			ids.add(map.get("roleUUID"));
		}

		Map resMap = new HashMap();
		resMap.put("treeData", TreeBeanUtil.getTreeBeanList(pageList));
		resMap.put("ids",ids);
		result.setSuccess(true);
		result.setResult(resMap);
		return result;
	}

	/**
	 *  查询设置界面显示的菜单权限
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryRoleMenu", method = RequestMethod.POST)
	public Result<Map> queryRoleMenu(@RequestParam(name ="id")String id,HttpServletRequest req) {

		UserBean userBean =  super.getUserBean(req);
		//根据当前id查询上级角色
		RoleInfo roleInfo =  sysRoleService.queryParentRole(userBean.getCorpCode(),id);
		if(roleInfo!=null){

		}
		//获取所有的菜单权限
		List<Map> menuModels = sysRoleService.queryAllMenusByCorpCode(userBean.getCorpCode());
		//获取当前角色已有的菜单权限
		List<String> roleUUIDList = sysRoleService.queryCurrentMenu(userBean.getCorpCode(),roleInfo.getRoleUUID());
		List ids = new ArrayList<>();
		Result<Map> result = new Result<Map>();
		for (Map map:menuModels){
			ids.add(map.get("menuCode"));
		}
		Map resMap = new HashMap();
		resMap.put("roleAllTrees", TreeUtil.getTreeModelList(menuModels,"menuCode","menuName"));
		resMap.put("roleUUIDList",roleUUIDList);
		resMap.put("ids",ids);
		result.setSuccess(true);
		result.setResult(resMap);
		return result;
	}

	/**
	 * 保存权限菜单
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveRolePermission", method = RequestMethod.POST)
	public Result<String> saveRolePermission(HttpServletRequest req) {
		Result<String> result = new Result<String>();
		try {
			UserBean userBean = this.getUserBean(req);
			String roleId = req.getParameter("roleId");
			String roleCode = req.getParameter("roleCode");
			String permissionIds = req.getParameter("permissionIds");
			String lastPermissionIds = req.getParameter("lastpermissionIds");
			//根据当前id查询上级角色
			roleMenuServce.saveRoleMenu(userBean.getCorpCode(),roleCode,permissionIds,lastPermissionIds);
			result.setSuccess(true);
			result.setMessage("设置成功");
		}catch (Exception e){
			 result.error500("设置失败");
			 log.error(e.getMessage(),e);
		}
		return result;
	}

	

	
	
}
