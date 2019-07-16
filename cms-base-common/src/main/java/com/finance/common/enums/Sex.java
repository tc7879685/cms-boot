package com.finance.common.enums;

import lombok.Getter;

@Getter
public enum Sex {

    MAN(0,"男"),
    WUMAN(1,"女");

    private  int code;

    private  String message;

    Sex(int code, String message) {
        this.code = code;
        this.message = message;
    }



    public static void main(String[] args) {

        System.out.println(Sex.values()[0]);
    }

}
