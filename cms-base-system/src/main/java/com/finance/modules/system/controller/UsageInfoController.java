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
import com.finance.modules.system.entity.UsageInfo;
import com.finance.modules.system.service.IUsageInfoService;
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
 * @Description: 用途维护
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/usageInfo")
public class UsageInfoController extends BaseController {
	@Autowired
	private IUsageInfoService usageInfoService;
	
	/**
	  * 分页列表查询
	 * @param usageInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用途维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<UsageInfo>> queryPageList(UsageInfo usageInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<UsageInfo>> result = new Result<IPage<UsageInfo>>();
		UserBean userBean =  super.getUserBean(req);
		usageInfo.setCorpCode(userBean.getCorpCode());
		QueryWrapper<UsageInfo> queryWrapper = QueryGenerator.initQueryWrapper(usageInfo, req.getParameterMap());
		Page<UsageInfo> page = new Page<UsageInfo>(pageNo, pageSize);
		IPage<UsageInfo> pageList = usageInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param usageInfo
	 * @return
	 */
	@AutoLog(value = "用途维护-添加")
	@PostMapping(value = "/add")
	public Result<UsageInfo> add(UsageInfo usageInfo,HttpServletRequest req) {
		Result<UsageInfo> result = new Result<UsageInfo>();
		UserBean userBean =  super.getUserBean(req);
        usageInfo.setCorpCode(userBean.getCorpCode());
		try {
			usageInfoService.save(usageInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param usageInfo
	 * @return
	 */
	@AutoLog(value = "用途维护-编辑")
	@PutMapping(value = "/edit")
	public Result<UsageInfo> edit(UsageInfo usageInfo) {
		Result<UsageInfo> result = new Result<UsageInfo>();
		UsageInfo usageInfoEntity = usageInfoService.getById(usageInfo.getUsageUUID());
		if(usageInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = usageInfoService.updateById(usageInfo);
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
	@AutoLog(value = "用途维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<UsageInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<UsageInfo> result = new Result<UsageInfo>();
		UsageInfo usageInfo = usageInfoService.getById(id);
		if(usageInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = usageInfoService.removeById(id);
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
	@AutoLog(value = "用途维护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<UsageInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<UsageInfo> result = new Result<UsageInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.usageInfoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用途维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<UsageInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<UsageInfo> result = new Result<UsageInfo>();
		UsageInfo usageInfo = usageInfoService.getById(id);
		if(usageInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(usageInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
