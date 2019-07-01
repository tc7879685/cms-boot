package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 菜单类信息
 * @author 齐卫刚
 * @version 1.0 2015-07-30
 * 	
 */
@Data
@TableName("plat_menu_info")
public class MenuInfo implements java.io.Serializable {

	private static final long serialVersionUID = -2487977747799626516L;
	
	@TableId
	private String menuCode;//菜单代码
	private String menuName;//菜单名称
	private String parentCode;//上级菜单
	private String menuLink;//链接页面
	private String iconSmall;//正常图标
	private String iconLarge;//标题图标
	private String authLink;//可用链接
	private String menuMemo;//备注信息
	private Integer showIndex;//菜单排序
	private Integer holdFlag;//客户使用标志0不使用1使用
	private Integer platFlag;//平台使用标志0不使用1使用
	private Integer statusCode;//使用状态 1正常0禁用
	

	
}
