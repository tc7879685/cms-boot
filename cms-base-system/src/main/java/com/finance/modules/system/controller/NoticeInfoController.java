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
import com.finance.modules.system.entity.NoticeInfo;
import com.finance.modules.system.service.INoticeInfoService;
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
 * @Description: 系统公告
 * @Author: cms-boot
 * @Date:   2019-07-16
 * @Version: V5.0
 */
@Slf4j
@RestController
@RequestMapping("/system/noticeInfo")
public class NoticeInfoController extends BaseController {
	@Autowired
	private INoticeInfoService noticeInfoService;
	
	/**
	  * 分页列表查询
	 * @param noticeInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "系统公告-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<NoticeInfo>> queryPageList(NoticeInfo noticeInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<NoticeInfo>> result = new Result<IPage<NoticeInfo>>();
		UserBean userBean =  super.getUserBean(req);
		noticeInfo.setCorpCode(userBean.getCorpCode());
		QueryWrapper<NoticeInfo> queryWrapper = QueryGenerator.initQueryWrapper(noticeInfo, req.getParameterMap());
		Page<NoticeInfo> page = new Page<NoticeInfo>(pageNo, pageSize);
		IPage<NoticeInfo> pageList = noticeInfoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param noticeInfo
	 * @return
	 */
	@AutoLog(value = "系统公告-添加")
	@PostMapping(value = "/add")
	public Result<NoticeInfo> add(NoticeInfo noticeInfo, HttpServletRequest req) {
		Result<NoticeInfo> result = new Result<NoticeInfo>();
		UserBean userBean =  super.getUserBean(req);
        noticeInfo.setCorpCode(userBean.getCorpCode());
		try {
			noticeInfoService.save(noticeInfo);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param noticeInfo
	 * @return
	 */
	@AutoLog(value = "系统公告-编辑")
	@PutMapping(value = "/edit")
	public Result<NoticeInfo> edit(NoticeInfo noticeInfo) {
		Result<NoticeInfo> result = new Result<NoticeInfo>();
		NoticeInfo noticeInfoEntity = noticeInfoService.getById(noticeInfo.getNoticeUUID());
		if(noticeInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = noticeInfoService.updateById(noticeInfo);
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
	@AutoLog(value = "系统公告-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<NoticeInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<NoticeInfo> result = new Result<NoticeInfo>();
		NoticeInfo noticeInfo = noticeInfoService.getById(id);
		if(noticeInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = noticeInfoService.removeById(id);
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
	@AutoLog(value = "系统公告-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<NoticeInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<NoticeInfo> result = new Result<NoticeInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.noticeInfoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "系统公告-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NoticeInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<NoticeInfo> result = new Result<NoticeInfo>();
		NoticeInfo noticeInfo = noticeInfoService.getById(id);
		if(noticeInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(noticeInfo);
			result.setSuccess(true);
		}
		return result;
	}


}
