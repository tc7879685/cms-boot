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
import com.finance.modules.system.entity.CurrencyInfo;
import com.finance.modules.system.service.ICurrencyInfoService;
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
 * @Description: 币别维护
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/currencyInfo")
public class CurrencyInfoController extends BaseController {
	@Autowired
	private ICurrencyInfoService currencyInfoService;
	
	/**
	  * 分页列表查询
	 * @param currencyInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "币别维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CurrencyInfo>> queryPageList(CurrencyInfo currencyInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CurrencyInfo>> result = new Result<IPage<CurrencyInfo>>();
		UserBean userBean =  super.getUserBean(req);
		currencyInfo.setCorpCode(userBean.getCorpCode());
		QueryWrapper<CurrencyInfo> queryWrapper = QueryGenerator.initQueryWrapper(currencyInfo, req.getParameterMap());
		Page<CurrencyInfo> page = new Page<CurrencyInfo>(pageNo, pageSize);
		IPage<CurrencyInfo> pageList = currencyInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param currencyInfo
	 * @return
	 */
	@AutoLog(value = "币别维护-添加")
	@PostMapping(value = "/add")
	public Result<CurrencyInfo> add(CurrencyInfo currencyInfo, HttpServletRequest req) {
		Result<CurrencyInfo> result = new Result<CurrencyInfo>();
		UserBean userBean =  super.getUserBean(req);
        currencyInfo.setCorpCode(userBean.getCorpCode());
		try {
			currencyInfoService.save(currencyInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param currencyInfo
	 * @return
	 */
	@AutoLog(value = "币别维护-编辑")
	@PutMapping(value = "/edit")
	public Result<CurrencyInfo> edit(CurrencyInfo currencyInfo) {
		Result<CurrencyInfo> result = new Result<CurrencyInfo>();
		CurrencyInfo currencyInfoEntity = currencyInfoService.getById(currencyInfo.getCurrencyUUID());
		if(currencyInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = currencyInfoService.updateById(currencyInfo);
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
	@AutoLog(value = "币别维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<CurrencyInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<CurrencyInfo> result = new Result<CurrencyInfo>();
		CurrencyInfo currencyInfo = currencyInfoService.getById(id);
		if(currencyInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = currencyInfoService.removeById(id);
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
	@AutoLog(value = "币别维护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<CurrencyInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CurrencyInfo> result = new Result<CurrencyInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.currencyInfoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "币别维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CurrencyInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CurrencyInfo> result = new Result<CurrencyInfo>();
		CurrencyInfo currencyInfo = currencyInfoService.getById(id);
		if(currencyInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(currencyInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
