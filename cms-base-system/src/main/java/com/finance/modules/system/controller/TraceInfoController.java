package com.finance.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.vo.Result;
import com.finance.common.system.query.QueryGenerator;
import com.finance.common.aspect.annotation.AutoLog;
import com.finance.modules.system.entity.TraceInfo;
import com.finance.modules.system.service.ITraceInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



 /**
 * @Description: 系统日志
 * @Author: cms-boot
 * @Date:   2019-07-15
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/traceInfo")
public class TraceInfoController extends BaseController {
	@Autowired
	private ITraceInfoService traceInfoService;
	
	/**
	  * 分页列表查询
	 * @param traceInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统日志-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TraceInfo>> queryPageList(TraceInfo traceInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TraceInfo>> result = new Result<IPage<TraceInfo>>();
		UserBean userBean = this.getUserBean(req);
		QueryWrapper<TraceInfo> queryWrapper = QueryGenerator.initQueryWrapper(traceInfo, req.getParameterMap());
		Page<TraceInfo> page = new Page<TraceInfo>(pageNo, pageSize);
		IPage<TraceInfo> pageList = traceInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param traceInfo
	 * @return
	 */
	@AutoLog(value = "系统日志-添加",logType =0)
	@PostMapping(value = "/add")
	public Result<TraceInfo> add(@RequestBody TraceInfo traceInfo) {
		Result<TraceInfo> result = new Result<TraceInfo>();
		try {
			traceInfoService.save(traceInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	



	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统日志-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TraceInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TraceInfo> result = new Result<TraceInfo>();
		TraceInfo traceInfo = traceInfoService.getById(id);
		if(traceInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(traceInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
