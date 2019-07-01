package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 当前使用的lombok
 * @Entity ,@Table使用在jpa中
 */
@Data
@TableName("sys_user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer userUUID;//主键
    private String corpCode;//机构
    private String userCode;//账号
    private String userPassword;//密码
    private String userName;//用户名称
    private String unitCode;//所属单位
    private Integer userSex;//性别 0无1男2女
    private String userJob;//职务
    private String userIdCard;//身份证
    private String userPhone;//电话
    private String userMobile;//手机
    private String userAddress;//地址
    private String userEmail;//邮箱
    private String userMemo;//备注
    private String workerCode;//关联员工
    private Date createTime;//注册时间
    private Date limitTime;//禁用时间
    private String certName;//证书名称
    private String certInfo;//证书内容
    private Date certBeginDate;//证书开始日期
    private Date certEndDate;//证书到期日期
    private Integer showIndex;//排序
    private Integer holdFlag;//保留 0可删 1保留
    private Integer statusCode;//状态 1正常0禁用
    private Integer attempts;//密码连续错误次数
    private Integer lockFlag;//0代表未锁定  1代表锁定
    private String sessionID;//当前登录主键
    private Integer certFlag;//用户结算管理制单是否绑定证书0-否1-是
    private Integer startLockFlag;//是否安全锁定0-否1-是
    private Integer lockNum;//几次锁定
    private Integer sendEmailNum;//已发送邮件次数
    private Date sendEmailDate;//发送邮件时间

}
