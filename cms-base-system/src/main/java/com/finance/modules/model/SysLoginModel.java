package com.finance.modules.model;

import lombok.Data;
/**
 * 登录表单
 *
 * @Author tangc
 * @since  2019-01-18
 */
@Data
public class SysLoginModel {

    private String corpCode; //机构号

    private String userCode;//用户名

    private String userPassword;//用户密码

    private String cert; //证书



}