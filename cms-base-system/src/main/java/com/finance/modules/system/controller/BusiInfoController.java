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
import com.finance.modules.system.entity.BusiInfo;
import com.finance.modules.system.service.IBusiInfoService;
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
 * @Description: 系统参数维护
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/busiInfo")
public class BusiInfoController extends BaseController {
	@Autowired
	private IBusiInfoService busiInfoService;
	
	/**
	  * 分页列表查询
	 * @param busiInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统参数维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BusiInfo>> queryPageList(BusiInfo busiInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<BusiInfo>> result = new Result<IPage<BusiInfo>>();
		UserBean userBean =  super.getUserBean(req);
		busiInfo.setCorpCode(userBean.getCorpCode());
		QueryWrapper<BusiInfo> queryWrapper = QueryGenerator.initQueryWrapper(busiInfo, req.getParameterMap());
		Page<BusiInfo> page = new Page<BusiInfo>(pageNo, pageSize);
		IPage<BusiInfo> pageList = busiInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param busiInfo
	 * @return
	 */
	@AutoLog(value = "系统参数维护-添加")
	@PostMapping(value = "/add")
	public Result<BusiInfo> add(BusiInfo busiInfo,HttpServletRequest req) {
		Result<BusiInfo> result = new Result<BusiInfo>();
		UserBean userBean =  super.getUserBean(req);
        busiInfo.setCorpCode(userBean.getCorpCode());
		try {
			busiInfoService.save(busiInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param busiInfo
	 * @return
	 */
	@AutoLog(value = "系统参数维护-编辑")
	@PutMapping(value = "/edit")
	public Result<BusiInfo> edit(BusiInfo busiInfo) {
		Result<BusiInfo> result = new Result<BusiInfo>();
		BusiInfo busiInfoEntity = busiInfoService.getById(busiInfo.getBusiUUID());
		if(busiInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = busiInfoService.updateById(busiInfo);
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
	@AutoLog(value = "系统参数维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<BusiInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<BusiInfo> result = new Result<BusiInfo>();
		BusiInfo busiInfo = busiInfoService.getById(id);
		if(busiInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = busiInfoService.removeById(id);
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
	@AutoLog(value = "系统参数维护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<BusiInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<BusiInfo> result = new Result<BusiInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.busiInfoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统参数维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BusiInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<BusiInfo> result = new Result<BusiInfo>();
		BusiInfo busiInfo = busiInfoService.getById(id);
		if(busiInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(busiInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
