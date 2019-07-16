//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.finance.codegenerate.generate.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.finance.codegenerate.database.DbReadTableUtil;
import com.finance.codegenerate.generate.IGenerate;
import com.finance.codegenerate.generate.impl.a.a;
import com.finance.codegenerate.generate.pojo.ColumnVo;
import com.finance.codegenerate.generate.pojo.TableVo;
import com.finance.codegenerate.generate.util.NonceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeGenerateOne extends a implements IGenerate {
    private static final Logger a = LoggerFactory.getLogger(CodeGenerateOne.class);
    private TableVo b;
    private List<ColumnVo> d;
    private List<ColumnVo> e;

    public CodeGenerateOne(TableVo tableVo) {
        this.b = tableVo;
    }

    public CodeGenerateOne(TableVo tableVo, List<ColumnVo> columns, List<ColumnVo> originalColumns) {
        this.b = tableVo;
        this.d = columns;
        this.e = originalColumns;
    }

    public Map<String, Object> a() throws Exception {
        HashMap var1 = new HashMap();
        var1.put("bussiPackage", com.finance.codegenerate.a.a.h);
        var1.put("entityPackage", this.b.getEntityPackage());
        var1.put("entityName", this.b.getEntityName());
        var1.put("tableName", this.b.getTableName());
        var1.put("primaryKeyField", com.finance.codegenerate.a.a.m);
        if (this.b.getFieldRequiredNum() == null) {
            this.b.setFieldRequiredNum(StringUtils.isNotEmpty(com.finance.codegenerate.a.a.n) ? Integer.parseInt(com.finance.codegenerate.a.a.n) : -1);
        }

        if (this.b.getSearchFieldNum() == null) {
            this.b.setSearchFieldNum(StringUtils.isNotEmpty(com.finance.codegenerate.a.a.o) ? Integer.parseInt(com.finance.codegenerate.a.a.o) : -1);
        }

        if (this.b.getFieldRowNum() == null) {
            this.b.setFieldRowNum(Integer.parseInt(com.finance.codegenerate.a.a.q));
        }

        var1.put("tableVo", this.b);

        try {
            if (this.d == null || this.d.size() == 0) {
                this.d = DbReadTableUtil.a(this.b.getTableName());
            }

            var1.put("columns", this.d);
            if (this.e == null || this.e.size() == 0) {
                this.e = DbReadTableUtil.b(this.b.getTableName());
            }

            var1.put("originalColumns", this.e);
            Iterator var2 = this.e.iterator();

            while(var2.hasNext()) {
                ColumnVo var3 = (ColumnVo)var2.next();
                if (var3.getFieldName().equals(com.finance.codegenerate.a.a.m)) {
                    var1.put("primaryKeyPolicy", var3.getFieldType());
                }
            }
        } catch (Exception var4) {
            throw var4;
        }

        long var5 = NonceUtils.c() + NonceUtils.g();
        var1.put("serialVersionUID", String.valueOf(var5));
        a.info("code template data: " + var1.toString());
        return var1;
    }

    public void generateCodeFile() throws Exception {
        a.info("----jeecg---Code----Generation----[单表模型:" + this.b.getTableName() + "]------- 生成中。。。");
        String var1 = com.finance.codegenerate.a.a.g;
        Map var2 = this.a();
        String var3 = com.finance.codegenerate.a.a.k;
        if (a(var3, "/").equals("jeecg/code-template")) {
            var3 = "/" + a(var3, "/") + "/one";
        }

        com.finance.codegenerate.generate.a.a var4 = new com.finance.codegenerate.generate.a.a(var3);
        this.a(var4, var1, var2);
        a.info("----jeecg----Code----Generation-----[单表模型：" + this.b.getTableName() + "]------ 生成完成。。。");
    }

    public void generateCodeFile(String projectPath, String templatePath) throws Exception {
        if (projectPath != null && !"".equals(projectPath)) {
            com.finance.codegenerate.a.a.a(projectPath);
        }

        if (templatePath != null && !"".equals(templatePath)) {
            com.finance.codegenerate.a.a.b(templatePath);
        }

        this.generateCodeFile();
    }

    public static void main(String[] args) {
        System.out.println("----jeecg--------- Code------------- Generation -----[单表模型]------- 生成中。。。");
        TableVo var1 = new TableVo();
        var1.setTableName("demo");
        var1.setPrimaryKeyPolicy("uuid");
        var1.setEntityPackage("test");
        var1.setEntityName("JeecgDemo");
        var1.setFtlDescription("jeecg 测试demo");

        try {
            (new CodeGenerateOne(var1)).generateCodeFile();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        System.out.println("----jeecg--------- Code------------- Generation -----[单表模型]------- 生成完成。。。");
    }
}
