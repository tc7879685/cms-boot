//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.finance.codegenerate.generate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.finance.codegenerate.database.DbReadTableUtil;
import com.finance.codegenerate.generate.IGenerate;
import com.finance.codegenerate.generate.impl.a.a;
import com.finance.codegenerate.generate.pojo.ColumnVo;
import com.finance.codegenerate.generate.pojo.onetomany.MainTableVo;
import com.finance.codegenerate.generate.pojo.onetomany.SubTableVo;
import com.finance.codegenerate.generate.util.NonceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeGenerateOneToMany extends a implements IGenerate {
    private static final Logger d = LoggerFactory.getLogger(CodeGenerateOneToMany.class);
    private static String e;
    public static String a = "A";
    public static String b = "B";
    private MainTableVo f;
    private List<ColumnVo> g;
    private List<ColumnVo> h;
    private List<SubTableVo> i;
    private static DbReadTableUtil j = new DbReadTableUtil();

    public CodeGenerateOneToMany(MainTableVo mainTableVo, List<SubTableVo> subTables) {
        this.i = subTables;
        this.f = mainTableVo;
    }

    public CodeGenerateOneToMany(MainTableVo mainTableVo, List<ColumnVo> mainColums, List<ColumnVo> originalMainColumns, List<SubTableVo> subTables) {
        this.f = mainTableVo;
        this.g = mainColums;
        this.h = originalMainColumns;
        this.i = subTables;
    }

    public Map<String, Object> a() throws Exception {
        HashMap var1 = new HashMap();
        var1.put("bussiPackage", com.finance.codegenerate.a.a.h);
        var1.put("entityPackage", this.f.getEntityPackage());
        var1.put("entityName", this.f.getEntityName());
        var1.put("tableName", this.f.getTableName());
        var1.put("ftl_description", this.f.getFtlDescription());
        var1.put("primaryKeyField", com.finance.codegenerate.a.a.m);
        if (this.f.getFieldRequiredNum() == null) {
            this.f.setFieldRequiredNum(StringUtils.isNotEmpty(com.finance.codegenerate.a.a.n) ? Integer.parseInt(com.finance.codegenerate.a.a.n) : -1);
        }

        if (this.f.getSearchFieldNum() == null) {
            this.f.setSearchFieldNum(StringUtils.isNotEmpty(com.finance.codegenerate.a.a.o) ? Integer.parseInt(com.finance.codegenerate.a.a.o) : -1);
        }

        if (this.f.getFieldRowNum() == null) {
            this.f.setFieldRowNum(Integer.parseInt(com.finance.codegenerate.a.a.q));
        }

        var1.put("tableVo", this.f);

        try {
            DbReadTableUtil var10001;
            if (this.g == null || this.g.size() == 0) {
                var10001 = j;
                this.g = DbReadTableUtil.a(this.f.getTableName());
            }

            if (this.h == null || this.h.size() == 0) {
                var10001 = j;
                this.h = DbReadTableUtil.b(this.f.getTableName());
            }

            var1.put("columns", this.g);
            var1.put("originalColumns", this.h);
            Iterator var2 = this.h.iterator();

            while(var2.hasNext()) {
                ColumnVo var3 = (ColumnVo)var2.next();
                if (var3.getFieldName().equals(com.finance.codegenerate.a.a.m)) {
                    var1.put("primaryKeyPolicy", var3.getFieldType());
                }
            }

            var2 = this.i.iterator();

            while(var2.hasNext()) {
                SubTableVo var12 = (SubTableVo)var2.next();
                List var4;
                DbReadTableUtil var10000;
                if (var12.getColums() == null || var12.getColums().size() == 0) {
                    var10000 = j;
                    var4 = DbReadTableUtil.a(var12.getTableName());
                    var12.setColums(var4);
                }

                if (var12.getOriginalColumns() == null || var12.getOriginalColumns().size() == 0) {
                    var10000 = j;
                    var4 = DbReadTableUtil.b(var12.getTableName());
                    var12.setOriginalColumns(var4);
                }

                String[] var13 = var12.getForeignKeys();
                ArrayList var5 = new ArrayList();
                String[] var6 = var13;
                int var7 = var13.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String var9 = var6[var8];
                    var10001 = j;
                    var5.add(DbReadTableUtil.d(var9));
                }

                var12.setForeignKeys((String[])var5.toArray(new String[0]));
                var12.setOriginalForeignKeys(var13);
            }

            var1.put("subTables", this.i);
        } catch (Exception var10) {
            throw var10;
        }

        long var11 = NonceUtils.c() + NonceUtils.g();
        var1.put("serialVersionUID", String.valueOf(var11));
        d.info("code template data: " + var1.toString());
        return var1;
    }

    public void generateCodeFile() throws Exception {
        d.info("----jeecg---Code----Generation----[一对多模型:" + this.f.getTableName() + "]------- 生成中。。。");
        String var1 = com.finance.codegenerate.a.a.g;
        Map var2 = this.a();
        String var3 = com.finance.codegenerate.a.a.k;
        if (a(var3, "/").equals("jeecg/code-template")) {
            var3 = "/" + a(var3, "/") + "/onetomany";
        }

        com.finance.codegenerate.generate.a.a var4 = new com.finance.codegenerate.generate.a.a(var3);
        this.a(var4, var1, var2);
        d.info("----jeecg----Code----Generation-----[一对多模型：" + this.f.getTableName() + "]------ 生成完成。。。");
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
}
