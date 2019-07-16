package com.finance.modules.model;

import lombok.Data;


@Data
public class UserModel {

    private String userUUID;//账号
    private String userCode;//账号
    private String userName;//用户名称
    private String unitCode;//所属单位
    private String unitName;//所属单位名称

    private String userJob;//职务
    private String userIdCard;//身份证
    private String userPhone;//电话
    private String userMobile;//手机
    private String userAddress;//地址
    private String userEmail;//邮箱
    private String userMemo;//备注
    private Integer statusCode;//状态 1正常0禁用
    private String statusName;//状态 1正常0禁用
    private Integer attempts;//密码连续错误次数
    private Integer lockFlag;//0代表未锁定  1代表锁定

    private Integer certFlag;//用户结算管理制单是否绑定证书0-否1-是
    private String  certName;//证书名称
    private Integer startLockFlag;//是否安全锁定0-否1-是
    private Integer lockNum;//几次锁定
    private Integer sendEmailNum;//已发送邮件次数


}
