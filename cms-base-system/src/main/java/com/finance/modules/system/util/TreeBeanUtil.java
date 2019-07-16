package com.finance.modules.system.util;

import com.finance.common.util.TreeUtil;

import java.util.List;
import java.util.Map;

public class TreeBeanUtil {


    public static List<Map> getTreeBeanList(List<Map> dataList){
        return TreeUtil.getTreeBeanList(dataList,"roleCode","parentCode","roleName","roleCode",true);
    }

    public static List<Map> getTreeUnitList(List<Map> dataList){
        return TreeUtil.getTreeBeanList(dataList,"unitCode","parentCode","unitName","unitCode",true);
    }

}
