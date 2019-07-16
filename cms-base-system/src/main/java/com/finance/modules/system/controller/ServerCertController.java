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
import com.finance.modules.system.entity.ServerCert;
import com.finance.modules.system.service.IServerCertService;
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
 * @Description: 机构证书
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/serverCert")
public class ServerCertController extends BaseController {
	@Autowired
	private IServerCertService serverCertService;
	
	/**
	  * 分页列表查询
	 * @param serverCert
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机构证书-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ServerCert>> queryPageList(ServerCert serverCert,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<ServerCert>> result = new Result<IPage<ServerCert>>();
		UserBean userBean =  super.getUserBean(req);
		serverCert.setCorpCode(userBean.getCorpCode());
		QueryWrapper<ServerCert> queryWrapper = QueryGenerator.initQueryWrapper(serverCert, req.getParameterMap());
		Page<ServerCert> page = new Page<ServerCert>(pageNo, pageSize);
		IPage<ServerCert> pageList = serverCertService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param serverCert
	 * @return
	 */
	@AutoLog(value = "机构证书-添加")
	@PostMapping(value = "/add")
	public Result<ServerCert> add(ServerCert serverCert,HttpServletRequest req) {
		Result<ServerCert> result = new Result<ServerCert>();
		UserBean userBean =  super.getUserBean(req);
        serverCert.setCorpCode(userBean.getCorpCode());
		try {
			serverCertService.save(serverCert);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param serverCert
	 * @return
	 */
	@AutoLog(value = "机构证书-编辑")
	@PutMapping(value = "/edit")
	public Result<ServerCert> edit(ServerCert serverCert) {
		Result<ServerCert> result = new Result<ServerCert>();
		ServerCert serverCertEntity = serverCertService.getById(serverCert.getCorpCode());
		if(serverCertEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = serverCertService.updateById(serverCert);
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
	@AutoLog(value = "机构证书-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<ServerCert> delete(@RequestParam(name="id",required=true) String id) {
		Result<ServerCert> result = new Result<ServerCert>();
		ServerCert serverCert = serverCertService.getById(id);
		if(serverCert==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = serverCertService.removeById(id);
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
	@AutoLog(value = "机构证书-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<ServerCert> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<ServerCert> result = new Result<ServerCert>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.serverCertService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构证书-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ServerCert> queryById(@RequestParam(name="id",required=true) String id) {
		Result<ServerCert> result = new Result<ServerCert>();
		ServerCert serverCert = serverCertService.getById(id);
		if(serverCert==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(serverCert);
			result.setSuccess(true);
		}
		return result;
	}


}
