//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.finance.codegenerate.window;

import java.util.ArrayList;
import com.finance.codegenerate.generate.impl.CodeGenerateOneToMany;
import com.finance.codegenerate.generate.pojo.onetomany.MainTableVo;
import com.finance.codegenerate.generate.pojo.onetomany.SubTableVo;

public class JeecgOneToMainUtil {
    public JeecgOneToMainUtil() {
    }

    public static void main(String[] args) {
        MainTableVo var1 = new MainTableVo();
        var1.setTableName("jeecg_order_main");
        var1.setEntityName("TestOrderMain");
        var1.setEntityPackage("test2");
        var1.setFtlDescription("订单");
        ArrayList var2 = new ArrayList();
        SubTableVo var3 = new SubTableVo();
        var3.setTableName("jeecg_order_customer");
        var3.setEntityName("TestOrderCustom");
        var3.setEntityPackage("test2");
        var3.setFtlDescription("客户明细");
        var3.setForeignKeys(new String[]{"order_id"});
        var2.add(var3);
        SubTableVo var4 = new SubTableVo();
        var4.setTableName("jeecg_order_ticket");
        var4.setEntityName("TestOrderTicket");
        var4.setEntityPackage("test2");
        var4.setFtlDescription("产品明细");
        var4.setForeignKeys(new String[]{"order_id"});
        var2.add(var4);
        var1.setSubTables(var2);

        try {
            (new CodeGenerateOneToMany(var1, var2)).generateCodeFile();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}
