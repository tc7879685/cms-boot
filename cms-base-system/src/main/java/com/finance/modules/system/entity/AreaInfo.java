package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;


/**
 * 
 * 地区类信息
 * 
 * @author 齐卫刚
 * @version 1.0 2015-04-17
 * 	
 */
@Data
@TableName("sys_area_info")
public class AreaInfo implements java.io.Serializable{

	private static final long serialVersionUID = -8818229241068552896L;

	@TableId
	private Integer areaUUID;//主键
	private String corpCode;//机构
	private String areaCode;//代码
	private String areaName;//名称
	private String parentCode;//上级
	private String areaMemo;//备注
	private Integer showIndex;//排序
	private Integer holdFlag;//保留 0可删 1保留
	private Integer statusCode;//状态 1正常0禁用
	

	@Transient
	private String parentName;//上级信息
	@Transient
	private String accountProvinceName;//账户所属省
	@Transient
	private String accountCityName;//账户所属市
	@Transient
	private String cityID;//账户所属市
	@Transient
	private String holdName;//保留信息
	@Transient
	private String statusName;//状态信息
	

	
}
