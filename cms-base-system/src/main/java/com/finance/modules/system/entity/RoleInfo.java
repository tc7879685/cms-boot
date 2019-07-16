package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * 
 * 角色类信息
 * 
 * @author 齐卫刚
 * @version 1.0 2015-04-16
 * 
 */
@Data
@TableName("sys_role_info")
public class RoleInfo implements java.io.Serializable {

	private static final long serialVersionUID = 7635011820377996003L;

	private Integer roleUUID;// 主键
	private String  corpCode;// 机构
	private String  roleCode;// 角色代码
	private String  roleName;// 角色名称
	private String  parentCode;// 上级角色
	private String  roleMemo;// 备注
	private Integer showIndex;// 排序
	private Integer holdFlag;// 保留 0可删 1保留
	private Integer statusCode;// 状态 1正常0禁用
	private Integer adminFlag;// 角色分类 0普通角色1管理员角色
	private Integer modifyFlag;// 是否可修改 0可修改1不可修改
	private Integer startLockFlag;//是否安全锁定0-否1-是
	private Integer lockNum;//安全锁定条件



}
