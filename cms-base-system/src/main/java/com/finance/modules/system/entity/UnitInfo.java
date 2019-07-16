package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

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

    @Id
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

    @Transient
    private Integer provinceUUID;//省份主键
    @Transient
    private Integer cityUUID;//城市主键
    @Transient
    private String provinceName;//省份名称
    @Transient
    private String cityName;//城市名称
    @Transient
    private String parentName;//上级信息
    @Transient
    private Integer selected;//是否选中
    @Transient
    private String holdName;//保留信息
    @Transient
    private String statusName;//状态信息
    @Transient
    private String typeName;//类别信息
    @Transient
    private Integer unitStatusCode;//状态 1正常0禁用 用于一条sql多个statusCode
    @Transient
    private List<UserInfo> userInfoList;//用户信息



}
