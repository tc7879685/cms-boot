package com.finance.common.system.api;


import com.finance.model.TableModel;

/**
 * @Description: 底层共通业务API，提供其他独立模块调用
 * @Author: tangc
 * @Date:2019-4-20 
 * @Version:V1.0
 */
public interface ISysBaseAPI {

	/**
	 * 日志添加
	 * @param corpCode
	 * @param userCode
	 * @param operateInfo
	 * @param operateType
	 */
	void addLog(String corpCode,String userCode,String operateInfo,Integer operateType);

	Integer getMaxID(TableModel tableModel);

	/**
	  * 根据用户账号查询登录用户信息
	 * @param username
	 * @return
	 *//*
	//public LoginUser getUserByName(String username);
	
	*//**
	 * 通过用户账号查询角色集合
	 * @param username
	 * @return
	 *//*
	public List<String> getRolesByUsername(String username);

	*//**
	 * 获取当前数据库类型
	 * @return
	 * @throws Exception 
	 *//*
	public String getDatabaseType() throws SQLException;
	
	*//**
	  * 获取数据字典
	 * @param code
	 * @return
	 *//*
	//public List<DictModel> queryDictItemsByCode(String code);

	*//**
	  * 获取表数据字典
	 * @param table
	 * @param text
	 * @param code
	 * @return
	 *//*
    //List<DictModel> queryTableDictItemsByCode(String table, String text, String code);
    
    *//**
   	 * 查询所有部门 作为字典信息 id -->value,departName -->text
   	 * @return
   	 *//*
   //	public List<DictModel> queryAllDepartBackDictModel();
   	
	*//**
	 * 发送系统消息
	 * @param fromUser 发送人(用户登录账户)
	 * @param toUser  发送给(用户登录账户)
	 * @param title  消息主题
	 * @param msgContent  消息内容
	 *//*
	public void sendSysAnnouncement(String fromUser, String toUser, String title, String msgContent);*/
}
