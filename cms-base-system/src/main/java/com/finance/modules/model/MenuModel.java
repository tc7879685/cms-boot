package com.finance.modules.model;

import lombok.Data;

/**
 * 菜单模型
 */
@Data
public class MenuModel {

    private  String menuCode;

    private String menuName;

    private Integer  showIndex;//排序

    private  String parentCode;

    private  String iconSmall; //图标

    private  String iconLarge;

    private String menuLink;//菜单链接

    private  String authLink;//授权链接

    private  String perms;//菜单按钮权限 （未启用）


    /*public MenuModel(boolean index) {
        if(index) {
            this.id = "9502685863ab87f0ad1134142788a385";
            this.name="首页";
            this.component="dashboard/Analysis";
            this.url="/dashboard/analysis";
            this.icon="home";
            this.menuType=0;
            this.sortNo=0;
            this.ruleFlag=0;
            this.delFlag=0;
            this.alwaysShow=false;
            this.route=true;
            this.leaf=true;
            this.hidden=false;
        }

    }*/


}
