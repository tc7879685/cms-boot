package com.finance.modules.system.aspect;

import com.alibaba.fastjson.JSONObject;
import com.finance.common.aspect.annotation.AutoLog;
import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.util.SpringContextUtils;
import com.finance.modules.model.SysLoginModel;
import com.finance.modules.system.entity.TraceInfo;
import com.finance.modules.system.entity.UserInfo;
import com.finance.modules.system.service.ITraceInfoService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 * 
 * @Author scott
 * @email jeecgos@163.com
 * @Date 2018年1月14日
 */
@Aspect
@Component
public class AutoLogAspect {
	@Autowired
	private ITraceInfoService sysLogService;

	//当添加注解时自动添加日志
	@Pointcut("@annotation(com.finance.common.aspect.annotation.AutoLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		TraceInfo sysLog = new TraceInfo();
		AutoLog syslog = method.getAnnotation(AutoLog.class);
		if(syslog != null){
			//注解上的描述,操作日志内容
			sysLog.setOperateInfo(syslog.value());
			sysLog.setOperateType(syslog.logType());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		//sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		/*Object[] args = joinPoint.getArgs();
		try{
			String params = JSONObject.toJSONString(args);
			sysLog.setRequestParam(params);
		}catch (Exception e){

		}*/

		//获取request
		//HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        //UserBean userBean = new BaseController().getUserBean(request);
		//设置IP地址
		//sysLog.setCorpCode(userBean.getCorpCode());
		//sysLog.setUserCode(userBean.getUserCode());

		//获取登录用户信息
        UserInfo sysUser = (UserInfo)SecurityUtils.getSubject().getPrincipal();
		if(sysUser!=null){
			sysLog.setCorpCode(sysUser.getCorpCode());
			sysLog.setUserCode(sysUser.getUserCode());

		}
		//耗时
		//sysLog.setCostTime(time);
		sysLog.setOperateTime(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}
}
