package com.finance.modules.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermissionTree implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;

	private String key;
	private String title;

	/**
	 * 父id
	 */
	private String parentId;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单权限编码
	 */
	private String perms;
	/**
	 * 权限策略1显示2禁用
	 */
	private String permsType;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 组件
	 */
	private String component;

	/**
	 * 跳转网页链接
	 */
	private String url;
	
	/**
	 * 一级菜单跳转地址
	 */
	private String redirect;

	/**
	 * 菜单排序
	 */
	private Integer sortNo;

	/**
	 * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
	 */
	private Integer menuType;

	/**
	 * 是否叶子节点: 1:是 0:不是
	 */
	private boolean isLeaf;
	
	/**
	 * 是否路由菜单: 0:不是  1:是（默认值1）
	 */
	private boolean route;

	/**
	 * 描述
	 */
	private String description;


	/**alwaysShow*/
    private boolean alwaysShow;
    /**是否隐藏路由菜单: 0否,1是（默认值0）*/
    private boolean hidden;
    


	
}
