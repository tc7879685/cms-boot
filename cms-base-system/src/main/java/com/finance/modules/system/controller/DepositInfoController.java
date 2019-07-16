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
import com.finance.modules.system.entity.DepositInfo;
import com.finance.modules.system.service.IDepositInfoService;
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
 * @Description: 联行号
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/depositInfo")
public class DepositInfoController extends BaseController {
	@Autowired
	private IDepositInfoService depositInfoService;
	
	/**
	  * 分页列表查询
	 * @param depositInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "联行号-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DepositInfo>> queryPageList(DepositInfo depositInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DepositInfo>> result = new Result<IPage<DepositInfo>>();
		QueryWrapper<DepositInfo> queryWrapper = QueryGenerator.initQueryWrapper(depositInfo, req.getParameterMap());
		Page<DepositInfo> page = new Page<DepositInfo>(pageNo, pageSize);
		IPage<DepositInfo> pageList = depositInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param depositInfo
	 * @return
	 */
	@AutoLog(value = "联行号-添加")
	@PostMapping(value = "/add")
	public Result<DepositInfo> add(DepositInfo depositInfo) {
		Result<DepositInfo> result = new Result<DepositInfo>();
		try {
			depositInfoService.save(depositInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param depositInfo
	 * @return
	 */
	@AutoLog(value = "联行号-编辑")
	@PutMapping(value = "/edit")
	public Result<DepositInfo> edit(DepositInfo depositInfo) {
		Result<DepositInfo> result = new Result<DepositInfo>();
		DepositInfo depositInfoEntity = depositInfoService.getById(depositInfo.getDepositCode());
		if(depositInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = depositInfoService.updateById(depositInfo);
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
	@AutoLog(value = "联行号-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<DepositInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<DepositInfo> result = new Result<DepositInfo>();
		DepositInfo depositInfo = depositInfoService.getById(id);
		if(depositInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = depositInfoService.removeById(id);
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
	@AutoLog(value = "联行号-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<DepositInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DepositInfo> result = new Result<DepositInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.depositInfoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "联行号-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<DepositInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DepositInfo> result = new Result<DepositInfo>();
		DepositInfo depositInfo = depositInfoService.getById(id);
		if(depositInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(depositInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
