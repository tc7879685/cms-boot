package com.finance.modules.system.service.impl;

import com.finance.common.constant.DefContants;
import com.finance.common.system.api.ISysBaseAPI;
import com.finance.common.system.controller.BaseController;
import com.finance.common.system.model.UserBean;
import com.finance.common.system.util.JwtUtil;
import com.finance.common.util.SpringContextUtils;
import com.finance.model.TableModel;
import com.finance.modules.model.SysLoginModel;
import com.finance.modules.system.entity.TraceInfo;
import com.finance.modules.system.mapper.SysBaseAPIMapper;
import com.finance.modules.system.mapper.TraceInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Description: 底层共通业务API，提供其他独立模块调用
 * @Author: tangc
 * @Date:2019-4-20 
 * @Version:V1.0
 */
@Slf4j
@Service
public class SysBaseApiImpl implements ISysBaseAPI {
	public static final String DB_TYPE_MYSQL="MYSQL";
	public static final String DB_TYPE_ORACLE="ORACLE";
	public static final String DB_TYPE_POSTGRESQL="POSTGRESQL";
	public static final String DB_TYPE_SQLSERVER="SQLSERVER";
	public static String DB_TYPE = "";
	
	@Resource
	private TraceInfoMapper sysLogMapper;
	@Resource
	private SysBaseAPIMapper sysBaseAPIMapper;
	/*private SysUserMapper userMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private ISysDictService sysDictService;
	@Resource
	private SysAnnouncementMapper sysAnnouncementMapper;
	@Resource
	private SysAnnouncementSendMapper sysAnnouncementSendMapper;*/
	
	@Override
	public void addLog(String corpCode,String userCode,String operateInfo,Integer operateType) {
		TraceInfo sysLog = new TraceInfo();
		//注解上的描述,操作日志内容
		sysLog.setOperateInfo(operateInfo);
		sysLog.setOperateType(operateType);
		sysLog.setCorpCode(corpCode);
		sysLog.setUserCode(userCode);

		/*//获取登录用户信息
		SysLoginModel sysUser = (SysLoginModel)SecurityUtils.getSubject().getPrincipal();
		if(sysUser!=null){
			sysLog.setCorpCode(sysUser.getCorpCode());
			sysLog.setUserCode(sysUser.getUserCode());
		}else{
			HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
			String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
			String[] usernameAyyay = JwtUtil.getUsername(token);
			sysLog.setCorpCode(usernameAyyay[0]);
			sysLog.setUserCode(usernameAyyay[1]);
		}*/
		sysLog.setOperateTime(new Date());
		//保存系统日志
		sysLogMapper.insert(sysLog);
	}

	@Override
	public Integer getMaxID(TableModel tableModel) {
		return sysBaseAPIMapper.getMaxId(tableModel);
	}

	/*@Override
	public LoginUser getUserByName(String username) {
		if(oConvertUtils.isEmpty(username)) {
			return null;
		}
		LoginUser loginUser = new LoginUser();
		SysUser sysUser = userMapper.getUserByName(username);
		if(sysUser==null) {
			return null;
		}
		BeanUtils.copyProperties(sysUser, loginUser);
		return loginUser;
	}

	@Override
	public List<String> getRolesByUsername(String username) {
		return sysUserRoleMapper.getRoleByUserName(username);
	}

	@Override
	public String getDatabaseType() throws SQLException {
		return getDatabaseType();
	}

	@Override
	public List<DictModel> queryDictItemsByCode(String code) {
		return sysDictService.queryDictItemsByCode(code);
	}

	@Override
	public List<DictModel> queryTableDictItemsByCode(String table, String text, String code) {
		return sysDictService.queryTableDictItemsByCode(table, text, code);
	}

	@Override
	public List<DictModel> queryAllDepartBackDictModel() {
		return sysDictService.queryAllDepartBackDictModel();
	}

	@Override
	public void sendSysAnnouncement(String fromUser, String toUser, String title, String msgContent) {
		SysAnnouncement announcement = new SysAnnouncement();
		announcement.setTitile(title);
		announcement.setMsgContent(msgContent);
		announcement.setSender(fromUser);
		announcement.setPriority(CommonConstant.PRIORITY_M);
		announcement.setMsgType(CommonConstant.MSG_TYPE_UESR);
		announcement.setSendStatus(CommonConstant.HAS_SEND);
		announcement.setSendTime(new Date());
		announcement.setMsgCategory("2");
		announcement.setDelFlag(String.valueOf(CommonConstant.DEL_FLAG_0));
		sysAnnouncementMapper.insert(announcement);
		// 2.插入用户通告阅读标记表记录
		String userId = toUser;
		String[] userIds = userId.split(",");
		String anntId = announcement.getId();
		for(int i=0;i<userIds.length;i++) {
			if(oConvertUtils.isNotEmpty(userIds[i])) {
				SysUser sysUser = userMapper.getUserByName(userIds[i]);
				if(sysUser==null) {
					continue;
				}
				SysAnnouncementSend announcementSend = new SysAnnouncementSend();
				announcementSend.setAnntId(anntId);
				announcementSend.setUserId(sysUser.getId());
				announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
				sysAnnouncementSendMapper.insert(announcementSend);
			}
		}
	}
	*//**
	 * 获取数据库类型
	 * @param dataSource
	 * @return
	 * @throws SQLException
	 * @throws DBException
	 *//*
	private static String getDatabaseType(DataSource dataSource) throws SQLException{
		if("".equals(DB_TYPE)) {
			Connection connection = dataSource.getConnection();
			try {
				DatabaseMetaData md = connection.getMetaData();
				String dbType = md.getDatabaseProductName().toLowerCase();
				if(dbType.indexOf("mysql")>=0) {
					DB_TYPE = DB_TYPE_MYSQL;
				}else if(dbType.indexOf("oracle")>=0) {
					DB_TYPE = DB_TYPE_ORACLE;
				}else if(dbType.indexOf("sqlserver")>=0||dbType.indexOf("sql server")>=0) {
					DB_TYPE = DB_TYPE_SQLSERVER;
				}else if(dbType.indexOf("postgresql")>=0) {
					DB_TYPE = DB_TYPE_POSTGRESQL;
				}else {
					throw new JeecgBootException("数据库类型:["+dbType+"]不识别!");
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}finally {
				connection.close();
			}
		}
		return DB_TYPE;
		
	}*/
}
