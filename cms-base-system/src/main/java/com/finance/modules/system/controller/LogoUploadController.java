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
import com.finance.modules.system.entity.LogoUpload;
import com.finance.modules.system.service.ILogoUploadService;
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
 * @Description: 存储的Logo
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/logoUpload")
public class LogoUploadController extends BaseController {
	@Autowired
	private ILogoUploadService logoUploadService;
	
	/**
	  * 分页列表查询
	 * @param logoUpload
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存储的Logo-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<LogoUpload>> queryPageList(LogoUpload logoUpload,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<LogoUpload>> result = new Result<IPage<LogoUpload>>();
		UserBean userBean =  super.getUserBean(req);
		logoUpload.setCorpCode(userBean.getCorpCode());
		QueryWrapper<LogoUpload> queryWrapper = QueryGenerator.initQueryWrapper(logoUpload, req.getParameterMap());
		Page<LogoUpload> page = new Page<LogoUpload>(pageNo, pageSize);
		IPage<LogoUpload> pageList = logoUploadService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param logoUpload
	 * @return
	 */
	@AutoLog(value = "存储的Logo-添加")
	@PostMapping(value = "/add")
	public Result<LogoUpload> add(LogoUpload logoUpload,HttpServletRequest req) {
		Result<LogoUpload> result = new Result<LogoUpload>();
		UserBean userBean =  super.getUserBean(req);
        logoUpload.setCorpCode(userBean.getCorpCode());
		try {
			logoUploadService.save(logoUpload);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param logoUpload
	 * @return
	 */
	@AutoLog(value = "存储的Logo-编辑")
	@PutMapping(value = "/edit")
	public Result<LogoUpload> edit(LogoUpload logoUpload) {
		Result<LogoUpload> result = new Result<LogoUpload>();
		LogoUpload logoUploadEntity = logoUploadService.getById(logoUpload.getLogoId());
		if(logoUploadEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = logoUploadService.updateById(logoUpload);
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
	@AutoLog(value = "存储的Logo-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<LogoUpload> delete(@RequestParam(name="id",required=true) String id) {
		Result<LogoUpload> result = new Result<LogoUpload>();
		LogoUpload logoUpload = logoUploadService.getById(id);
		if(logoUpload==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = logoUploadService.removeById(id);
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
	@AutoLog(value = "存储的Logo-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<LogoUpload> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<LogoUpload> result = new Result<LogoUpload>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.logoUploadService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存储的Logo-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<LogoUpload> queryById(@RequestParam(name="id",required=true) String id) {
		Result<LogoUpload> result = new Result<LogoUpload>();
		LogoUpload logoUpload = logoUploadService.getById(id);
		if(logoUpload==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(logoUpload);
			result.setSuccess(true);
		}
		return result;
	}


}
