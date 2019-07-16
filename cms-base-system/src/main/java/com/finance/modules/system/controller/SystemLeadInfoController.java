package com.finance.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;

import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import javax.servlet.http.HttpServletResponse;
import com.finance.common.vo.Result;
import com.finance.common.system.query.QueryGenerator;
import com.finance.common.aspect.annotation.AutoLog;
import com.finance.common.util.oConvertUtils;
import com.finance.modules.system.entity.SystemLeadInfo;
import com.finance.modules.system.service.ISystemLeadInfoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.finance.common.aspect.annotation.AutoLog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;


 /**
 * @Description: 系统引导
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/systemLeadInfo")
public class SystemLeadInfoController extends BaseController {
	@Autowired
	private ISystemLeadInfoService systemLeadInfoService;
	
	/**
	  * 分页列表查询
	 * @param systemLeadInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统引导-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SystemLeadInfo>> queryPageList(SystemLeadInfo systemLeadInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<SystemLeadInfo>> result = new Result<IPage<SystemLeadInfo>>();
		UserBean userBean =  super.getUserBean(req);
		systemLeadInfo.setCorpCode(userBean.getCorpCode());
		QueryWrapper<SystemLeadInfo> queryWrapper = QueryGenerator.initQueryWrapper(systemLeadInfo, req.getParameterMap());
		Page<SystemLeadInfo> page = new Page<SystemLeadInfo>(pageNo, pageSize);
		IPage<SystemLeadInfo> pageList = systemLeadInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param systemLeadInfo
	 * @return
	 */
	@AutoLog(value = "系统引导-添加")
	@PostMapping(value = "/add")
	public Result<SystemLeadInfo> add(SystemLeadInfo systemLeadInfo,HttpServletRequest req) {
		Result<SystemLeadInfo> result = new Result<SystemLeadInfo>();
		UserBean userBean =  super.getUserBean(req);
        systemLeadInfo.setCorpCode(userBean.getCorpCode());
		try {
			systemLeadInfoService.save(systemLeadInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param systemLeadInfo
	 * @return
	 */
	@AutoLog(value = "系统引导-编辑")
	@PutMapping(value = "/edit")
	public Result<SystemLeadInfo> edit(SystemLeadInfo systemLeadInfo) {
		Result<SystemLeadInfo> result = new Result<SystemLeadInfo>();
		SystemLeadInfo systemLeadInfoEntity = systemLeadInfoService.getById(systemLeadInfo.getLeadUUID());
		if(systemLeadInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = systemLeadInfoService.updateById(systemLeadInfo);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统引导-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<SystemLeadInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<SystemLeadInfo> result = new Result<SystemLeadInfo>();
		SystemLeadInfo systemLeadInfo = systemLeadInfoService.getById(id);
		if(systemLeadInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = systemLeadInfoService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "系统引导-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<SystemLeadInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<SystemLeadInfo> result = new Result<SystemLeadInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.systemLeadInfoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统引导-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SystemLeadInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<SystemLeadInfo> result = new Result<SystemLeadInfo>();
		SystemLeadInfo systemLeadInfo = systemLeadInfoService.getById(id);
		if(systemLeadInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(systemLeadInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
