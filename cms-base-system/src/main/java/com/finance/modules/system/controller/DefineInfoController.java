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
import com.finance.modules.system.entity.DefineInfo;
import com.finance.modules.system.service.IDefineInfoService;
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
@RequestMapping("/system/defineInfo")
public class DefineInfoController extends BaseController {
	@Autowired
	private IDefineInfoService defineInfoService;
	
	/**
	  * 分页列表查询
	 * @param defineInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统参数维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<DefineInfo>> queryPageList(DefineInfo defineInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DefineInfo>> result = new Result<IPage<DefineInfo>>();
		UserBean userBean =  super.getUserBean(req);
		defineInfo.setCorpCode(userBean.getCorpCode());
		QueryWrapper<DefineInfo> queryWrapper = QueryGenerator.initQueryWrapper(defineInfo, req.getParameterMap());
		Page<DefineInfo> page = new Page<DefineInfo>(pageNo, pageSize);
		IPage<DefineInfo> pageList = defineInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param defineInfo
	 * @return
	 */
	@AutoLog(value = "系统参数维护-添加")
	@PostMapping(value = "/add")
	public Result<DefineInfo> add(DefineInfo defineInfo,HttpServletRequest req) {
		Result<DefineInfo> result = new Result<DefineInfo>();
		UserBean userBean =  super.getUserBean(req);
        defineInfo.setCorpCode(userBean.getCorpCode());
		try {
			defineInfoService.save(defineInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param defineInfo
	 * @return
	 */
	@AutoLog(value = "系统参数维护-编辑")
	@PutMapping(value = "/edit")
	public Result<DefineInfo> edit(DefineInfo defineInfo) {
		/*Result<DefineInfo> result = new Result<DefineInfo>();
		DefineInfo defineInfoEntity = defineInfoService.getById(defineInfo.getId());
		if(defineInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = defineInfoService.updateById(defineInfo);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}*/
		
		return null;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统参数维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<DefineInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<DefineInfo> result = new Result<DefineInfo>();
		DefineInfo defineInfo = defineInfoService.getById(id);
		if(defineInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = defineInfoService.removeById(id);
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
	public Result<DefineInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DefineInfo> result = new Result<DefineInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.defineInfoService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DefineInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DefineInfo> result = new Result<DefineInfo>();
		DefineInfo defineInfo = defineInfoService.getById(id);
		if(defineInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(defineInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
