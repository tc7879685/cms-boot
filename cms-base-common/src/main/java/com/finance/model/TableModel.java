package com.finance.model;

import lombok.Data;

/**
 * 查询最大ID值模板
 */
@Data
public class TableModel {


    String tableName;

    String filed;


    public  TableModel(){}

    public  TableModel(String tableName,String filed){

        this.tableName = tableName;
        this.filed = filed;
    }
}
