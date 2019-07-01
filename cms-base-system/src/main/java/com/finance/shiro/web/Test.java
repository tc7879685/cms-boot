package com.finance.shiro.web;

import com.finance.common.util.PasswordUtil;

public class Test {

    public static  void  main(String[] args){
        String password = PasswordUtil.encrypt("admin","1");
        System.out.println(password);
        System.out.println( PasswordUtil.decrypt(password,"1"));
    }
}
