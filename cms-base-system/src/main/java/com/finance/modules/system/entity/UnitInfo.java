package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;



/**
 *
 * 单位类信息
 *
 * @author 齐卫刚
 * @version 1.0 2015-03-18
 *
 */
@Data
@TableName("sys_unit_info")
public class UnitInfo implements java.io.Serializable {

    private static final long serialVersionUID = 2355100185935699489L;

    @TableId
    private Integer unitUUID;//主键
    private String corpCode;//机构
    private String unitCode;//单位代码
    private String unitName;//单位名称
    private String parentCode;//上级单位
    private Integer unitType;//单位类别 0集团企业1成员单位2二级企业3部门
    private String unitProvince;//省
    private String unitCity;//市
    private String organCode;//组织机构代码
    private String unitAddress;//地址
    private String unitPhone;//电话
    private String unitFax;//传真
    private String unitContact;//联系人
    private String unitMemo;//备注
    private Integer showIndex;//排序
    private Integer holdFlag;//保留 0可删 1保留
    private Integer statusCode;//状态 1正常0禁用

    @TableField(exist = false) //表示不再字段映射内
    private Integer provinceUUID;//省份主键
    @TableField(exist = false)
    private Integer cityUUID;//城市主键
    @TableField(exist = false)
    private String provinceName;//省份名称
    @TableField(exist = false)
    private String cityName;//城市名称
    @TableField(exist = false)
    private String parentName;//上级信息
    @TableField(exist = false)
    private Integer selected;//是否选中
    @TableField(exist = false)
    private String holdName;//保留信息
    @TableField(exist = false)
    private String statusName;//状态信息
    @TableField(exist = false)
    private String typeName;//类别信息
    @TableField(exist = false)
    private Integer unitStatusCode;//状态 1正常0禁用 用于一条sql多个statusCode
    @TableField(exist = false)
    private List<UserInfo> userInfoList;//用户信息



}
