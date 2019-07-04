package com.finance;

import com.finance.common.util.PasswordUtil;

public class Test {

    public static void main(String[] args) {

        System.out.println(PasswordUtil.encrypt("admin", "1"));
    }
}
