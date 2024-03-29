//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.finance.codegenerate.database;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import com.finance.codegenerate.a.a;
import com.finance.codegenerate.generate.pojo.ColumnVo;
import com.finance.codegenerate.generate.util.c;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbReadTableUtil {
    private static final Logger a = LoggerFactory.getLogger(DbReadTableUtil.class);
    private static Connection b;
    private static Statement c;

    public DbReadTableUtil() {
    }

    public static void main(String[] args) throws SQLException {
        try {
            List var1 = a("demo");
            Iterator var2 = var1.iterator();

            while(var2.hasNext()) {
                ColumnVo var3 = (ColumnVo)var2.next();
                System.out.println(var3.getFieldName());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        PrintStream var10000 = System.out;
        new DbReadTableUtil();
        var10000.println(ArrayUtils.toString(a()));
    }

    public static List<String> a() throws SQLException {
        String var1 = null;
        ArrayList var2 = new ArrayList(0);

        try {
            Class.forName(com.finance.codegenerate.a.a.b);
            b = DriverManager.getConnection(com.finance.codegenerate.a.a.c, com.finance.codegenerate.a.a.d, com.finance.codegenerate.a.a.e);
            c = b.createStatement(1005, 1007);
            if (com.finance.codegenerate.a.a.a.equals("mysql")) {
                var1 = MessageFormat.format("select distinct table_name from information_schema.columns where table_schema = {0}", com.finance.codegenerate.generate.util.c.c(com.finance.codegenerate.a.a.f));
            }

            if (com.finance.codegenerate.a.a.a.equals("oracle")) {
                var1 = " select distinct colstable.table_name as  table_name from user_tab_cols colstable order by colstable.table_name";
            }

            if (com.finance.codegenerate.a.a.a.equals("postgresql")) {
                var1 = "SELECT distinct c.relname AS  table_name FROM pg_class c";
            }

            if (com.finance.codegenerate.a.a.a.equals("sqlserver")) {
                var1 = "select distinct c.name as  table_name from sys.objects c where c.type = 'U' ";
            }

            ResultSet var0 = c.executeQuery(var1);

            while(var0.next()) {
                String var3 = var0.getString(1);
                var2.add(var3);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                    c = null;
                    System.gc();
                }

                if (b != null) {
                    b.close();
                    b = null;
                    System.gc();
                }
            } catch (SQLException var11) {
                throw var11;
            }

        }

        return var2;
    }

    public static List<ColumnVo> a(String var0) throws Exception {
        String var2 = null;
        ArrayList var3 = new ArrayList();

        ColumnVo var6;
        try {
            Class.forName(com.finance.codegenerate.a.a.b);
            b = DriverManager.getConnection(com.finance.codegenerate.a.a.c, com.finance.codegenerate.a.a.d, com.finance.codegenerate.a.a.e);
            c = b.createStatement(1005, 1007);
            if (com.finance.codegenerate.a.a.a.equals("mysql")) {
                var2 = MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}", com.finance.codegenerate.generate.util.c.c(var0), com.finance.codegenerate.generate.util.c.c(com.finance.codegenerate.a.a.f));
            }

            if (com.finance.codegenerate.a.a.a.equals("oracle")) {
                var2 = MessageFormat.format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}", com.finance.codegenerate.generate.util.c.c(var0));
            }

            if (com.finance.codegenerate.a.a.a.equals("postgresql")) {
                var2 = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", com.finance.codegenerate.generate.util.c.c(var0));
            }

            if (com.finance.codegenerate.a.a.a.equals("sqlserver")) {
                var2 = MessageFormat.format("select distinct cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable,column_id   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join (select top 1 * from sys.objects where type = '''U''' and name ={0}  order by name) c on a.object_id=c.object_id left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0} order by a.column_id", com.finance.codegenerate.generate.util.c.c(var0));
            }

            ResultSet var1 = c.executeQuery(var2);
            var1.last();
            int var4 = var1.getRow();
            if (var4 <= 0) {
                throw new Exception("该表不存在或者表中没有字段");
            }

            var6 = new ColumnVo();
            if (com.finance.codegenerate.a.a.l) {
                var6.setFieldName(e(var1.getString(1)));
            } else {
                var6.setFieldName(var1.getString(1));
            }

            var6.setFieldDbName(var1.getString(1));
            var6.setFieldType(e(var1.getString(2)));
            var6.setFieldDbType(e(var1.getString(2)));
            var6.setPrecision(var1.getString(4));
            var6.setScale(var1.getString(5));
            var6.setCharmaxLength(var1.getString(6));
            var6.setNullable(com.finance.codegenerate.generate.util.c.a(var1.getString(7)));
            a(var6);
            var6.setFiledComment(StringUtils.isBlank(var1.getString(3)) ? var6.getFieldName() : var1.getString(3));
            a.debug("columnt.getFieldName() -------------" + var6.getFieldName());
            String[] var7 = new String[0];
            if (com.finance.codegenerate.a.a.p != null) {
                var7 = com.finance.codegenerate.a.a.p.split(",");
            }

            if (!com.finance.codegenerate.a.a.m.equals(var6.getFieldName()) && !com.finance.codegenerate.database.util.a.a(var6.getFieldDbName(), var7)) {
                var3.add(var6);
            }

            while(var1.previous()) {
                ColumnVo var8 = new ColumnVo();
                if (com.finance.codegenerate.a.a.l) {
                    var8.setFieldName(e(var1.getString(1)));
                } else {
                    var8.setFieldName(var1.getString(1));
                }

                var8.setFieldDbName(var1.getString(1));
                a.debug("columnt.getFieldName() -------------" + var8.getFieldName());
                if (!com.finance.codegenerate.a.a.m.equals(var8.getFieldName()) && !com.finance.codegenerate.database.util.a.a(var8.getFieldDbName(), var7)) {
                    var8.setFieldType(e(var1.getString(2)));
                    var8.setFieldDbType(e(var1.getString(2)));
                    a.debug("-----po.setFieldType------------" + var8.getFieldType());
                    var8.setPrecision(var1.getString(4));
                    var8.setScale(var1.getString(5));
                    var8.setCharmaxLength(var1.getString(6));
                    var8.setNullable(com.finance.codegenerate.generate.util.c.a(var1.getString(7)));
                    a(var8);
                    var8.setFiledComment(StringUtils.isBlank(var1.getString(3)) ? var8.getFieldName() : var1.getString(3));
                    var3.add(var8);
                }
            }

            a.debug("读取表成功");
        } catch (ClassNotFoundException var17) {
            throw var17;
        } catch (SQLException var18) {
            throw var18;
        } finally {
            try {
                if (c != null) {
                    c.close();
                    c = null;
                    System.gc();
                }

                if (b != null) {
                    b.close();
                    b = null;
                    System.gc();
                }
            } catch (SQLException var16) {
                throw var16;
            }

        }

        ArrayList var20 = new ArrayList();

        for(int var5 = var3.size() - 1; var5 >= 0; --var5) {
            var6 = (ColumnVo)var3.get(var5);
            var20.add(var6);
        }

        return var20;
    }

    public static List<ColumnVo> b(String var0) throws Exception {
        ResultSet var1 = null;
        String var2 = null;
        ArrayList var3 = new ArrayList();

        ColumnVo var6;
        try {
            Class.forName(com.finance.codegenerate.a.a.b);
            b = DriverManager.getConnection(com.finance.codegenerate.a.a.c, com.finance.codegenerate.a.a.d, com.finance.codegenerate.a.a.e);
            c = b.createStatement(1005, 1007);
            if (com.finance.codegenerate.a.a.a.equals("mysql")) {
                var2 = MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}", com.finance.codegenerate.generate.util.c.c(var0), com.finance.codegenerate.generate.util.c.c(com.finance.codegenerate.a.a.f));
            }

            if (com.finance.codegenerate.a.a.a.equals("oracle")) {
                var2 = MessageFormat.format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}", com.finance.codegenerate.generate.util.c.c(var0));
            }

            if (com.finance.codegenerate.a.a.a.equals("postgresql")) {
                var2 = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", com.finance.codegenerate.generate.util.c.c(var0));
            }

            if (com.finance.codegenerate.a.a.a.equals("sqlserver")) {
                var2 = MessageFormat.format("select distinct cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable,column_id   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join (select top 1 * from sys.objects where type = '''U''' and name ={0}  order by name) c on a.object_id=c.object_id left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0} order by a.column_id", com.finance.codegenerate.generate.util.c.c(var0));
            }

            var1 = c.executeQuery(var2);
            var1.last();
            int var4 = var1.getRow();
            if (var4 <= 0) {
                throw new Exception("该表不存在或者表中没有字段");
            }

            var6 = new ColumnVo();
            if (com.finance.codegenerate.a.a.l) {
                var6.setFieldName(e(var1.getString(1)));
            } else {
                var6.setFieldName(var1.getString(1));
            }

            var6.setFieldDbName(var1.getString(1));
            var6.setPrecision(com.finance.codegenerate.generate.util.c.b(var1.getString(4)));
            var6.setScale(com.finance.codegenerate.generate.util.c.b(var1.getString(5)));
            var6.setCharmaxLength(com.finance.codegenerate.generate.util.c.b(var1.getString(6)));
            var6.setNullable(com.finance.codegenerate.generate.util.c.a(var1.getString(7)));
            var6.setFieldType(a(var1.getString(2), var6.getPrecision(), var6.getScale()));
            var6.setFieldDbType(e(var1.getString(2)));
            a(var6);
            var6.setFiledComment(StringUtils.isBlank(var1.getString(3)) ? var6.getFieldName() : var1.getString(3));
            a.debug("columnt.getFieldName() -------------" + var6.getFieldName());
            var3.add(var6);

            while(true) {
                if (!var1.previous()) {
                    a.debug("读取表成功");
                    break;
                }

                ColumnVo var7 = new ColumnVo();
                if (com.finance.codegenerate.a.a.l) {
                    var7.setFieldName(e(var1.getString(1)));
                } else {
                    var7.setFieldName(var1.getString(1));
                }

                var7.setFieldDbName(var1.getString(1));
                var7.setPrecision(com.finance.codegenerate.generate.util.c.b(var1.getString(4)));
                var7.setScale(com.finance.codegenerate.generate.util.c.b(var1.getString(5)));
                var7.setCharmaxLength(com.finance.codegenerate.generate.util.c.b(var1.getString(6)));
                var7.setNullable(com.finance.codegenerate.generate.util.c.a(var1.getString(7)));
                var7.setFieldType(a(var1.getString(2), var7.getPrecision(), var7.getScale()));
                var7.setFieldDbType(e(var1.getString(2)));
                a(var7);
                var7.setFiledComment(StringUtils.isBlank(var1.getString(3)) ? var7.getFieldName() : var1.getString(3));
                var3.add(var7);
            }
        } catch (ClassNotFoundException var16) {
            throw var16;
        } catch (SQLException var17) {
            throw var17;
        } finally {
            try {
                if (c != null) {
                    c.close();
                    c = null;
                    System.gc();
                }

                if (b != null) {
                    b.close();
                    b = null;
                    System.gc();
                }
            } catch (SQLException var15) {
                throw var15;
            }

        }

        ArrayList var19 = new ArrayList();

        for(int var5 = var3.size() - 1; var5 >= 0; --var5) {
            var6 = (ColumnVo)var3.get(var5);
            var19.add(var6);
        }

        return var19;
    }

    public static boolean c(String var0) {
        String var2 = null;

        try {
            a.debug("数据库驱动: " + com.finance.codegenerate.a.a.b);
            Class.forName(com.finance.codegenerate.a.a.b);
            b = DriverManager.getConnection(com.finance.codegenerate.a.a.c, com.finance.codegenerate.a.a.d, com.finance.codegenerate.a.a.e);
            c = b.createStatement(1005, 1007);
            if (com.finance.codegenerate.a.a.a.equals("mysql")) {
                var2 = "select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '" + var0 + "' and table_schema = '" + com.finance.codegenerate.a.a.f + "'";
            }

            if (com.finance.codegenerate.a.a.a.equals("oracle")) {
                var2 = "select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = '" + var0 + "'";
            }

            if (com.finance.codegenerate.a.a.a.equals("postgresql")) {
                var2 = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", com.finance.codegenerate.generate.util.c.c(var0));
            }

            if (com.finance.codegenerate.a.a.a.equals("sqlserver")) {
                var2 = MessageFormat.format("select distinct cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable,column_id   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join (select top 1 * from sys.objects where type = '''U''' and name ={0}  order by name) c on a.object_id=c.object_id left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0} order by a.column_id", com.finance.codegenerate.generate.util.c.c(var0));
            }

            ResultSet var1 = c.executeQuery(var2);
            var1.last();
            int var3 = var1.getRow();
            return var3 > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    private static String e(String var0) {
        String[] var1 = var0.split("_");
        var0 = "";
        int var2 = 0;

        for(int var3 = var1.length; var2 < var3; ++var2) {
            if (var2 > 0) {
                String var4 = var1[var2];
                var4 = var4.substring(0, 1) + var4.substring(1, var4.length());
                var0 = var0 + var4;
            } else {
                var0 = var0 + var1[var2];
            }
        }

        return var0;
    }

    public static String d(String var0) {
        String[] var1 = var0.split("_");
        var0 = "";
        int var2 = 0;

        for(int var3 = var1.length; var2 < var3; ++var2) {
            if (var2 > 0) {
                String var4 = var1[var2];
                var4 = var4.substring(0, 1) + var4.substring(1, var4.length());
                var0 = var0 + var4;
            } else {
                var0 = var0 + var1[var2];
            }
        }

        var0 = var0.substring(0, 1) + var0.substring(1);
        return var0;
    }

    private static void a(ColumnVo var0) {
        String var1 = var0.getFieldType();
        String var2 = var0.getScale();
        var0.setClassType("inputxt");
        if ("N".equals(var0.getNullable())) {
            var0.setOptionType("*");
        }

        if (!"datetime".equals(var1) && !var1.contains("time")) {
            if ("date".equals(var1)) {
                var0.setClassType("easyui-datebox");
            } else if (var1.contains("int")) {
                var0.setOptionType("n");
            } else if ("number".equals(var1)) {
                if (StringUtils.isNotBlank(var2) && Integer.parseInt(var2) > 0) {
                    var0.setOptionType("d");
                }
            } else if (!"float".equals(var1) && !"double".equals(var1) && !"decimal".equals(var1)) {
                if ("numeric".equals(var1)) {
                    var0.setOptionType("d");
                }
            } else {
                var0.setOptionType("d");
            }
        } else {
            var0.setClassType("easyui-datetimebox");
        }

    }

    private static String a(String var0, String var1, String var2) {
        if (var0.contains("char")) {
            var0 = "java.lang.String";
        } else if (var0.contains("int")) {
            var0 = "java.lang.Integer";
        } else if (var0.contains("float")) {
            var0 = "java.lang.Float";
        } else if (var0.contains("double")) {
            var0 = "java.lang.Double";
        } else if (var0.contains("number")) {
            if (StringUtils.isNotBlank(var2) && Integer.parseInt(var2) > 0) {
                var0 = "java.math.BigDecimal";
            } else if (StringUtils.isNotBlank(var1) && Integer.parseInt(var1) > 10) {
                var0 = "java.lang.Long";
            } else {
                var0 = "java.lang.Integer";
            }
        } else if (var0.contains("decimal")) {
            var0 = "java.math.BigDecimal";
        } else if (var0.contains("date")) {
            var0 = "java.util.Date";
        } else if (var0.contains("time")) {
            var0 = "java.util.Date";
        } else if (var0.contains("blob")) {
            var0 = "byte[]";
        } else if (var0.contains("clob")) {
            var0 = "java.sql.Clob";
        } else if (var0.contains("numeric")) {
            var0 = "java.math.BigDecimal";
        } else {
            var0 = "java.lang.Object";
        }

        return var0;
    }
}
