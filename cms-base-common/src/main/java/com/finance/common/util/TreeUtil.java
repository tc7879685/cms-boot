package com.finance.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finance.model.TreeModel;

import java.util.*;

public class TreeUtil {


    public static List<TreeModel> getTreeModelList(List<Map> treeModels,String code,String name){
        List<TreeModel> lists = new ArrayList<TreeModel>();
        for(Map entity :treeModels){
            JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
            String parentCode = menuJSON.getString("parentCode");
            TreeModel treeModel = new TreeModel();
            if(parentCode.equals("0")){
                //当为0则是父
                    treeModel.setParentId(parentCode);
                    treeModel.setKey(menuJSON.getString(code));
                    treeModel.setValue(menuJSON.getString(name));
                    treeModel.setTitle(menuJSON.getString(name));
                    treeModel.setChildren(menuChild(treeModels,treeModel.getKey(),code,name));
                    lists.add(treeModel);
            }
        }
        return lists;
    }

    private static List<TreeModel> menuChild(List<Map> treeModels,String id,String code,String name) {
        List<TreeModel> lists = new ArrayList<TreeModel>();
        for (Map entity :treeModels) {
            JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
            TreeModel treeModel = new TreeModel();
            String parentCode = menuJSON.getString("parentCode");
            if (parentCode.equals(id)) {
                    treeModel.setParentId(menuJSON.getString("parentCode"));
                    treeModel.setKey(menuJSON.getString(code));
                    treeModel.setValue(menuJSON.getString(name));
                    treeModel.setTitle(menuJSON.getString(name));

                    treeModel.setChildren(menuChild(treeModels,treeModel.getKey(),code,name));
                    lists.add(treeModel);
            }
        }
        return lists;
    }


    public static List<Map> getTreeMapList(List<Map> treeModels,String code,String name){
        List<Map> lists = new ArrayList<Map>();
        for(Map entity :treeModels){
            Iterator<String> itr = entity.keySet().iterator();
            JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
            String parentCode = menuJSON.getString("parentCode");
            if(parentCode.equals("0")){
                //当为0则是父
                Map treeBean = new HashMap();
                while(itr.hasNext()){
                    String key = itr.next();
                    treeBean.put(key, entity.get(key));
                }
                treeBean.put("key",menuJSON.getString(code));
                treeBean.put("title",menuJSON.getString(name));
                treeBean.put("value",menuJSON.getString(name));
                treeBean.put("children",menuChildMap(treeModels,menuJSON.getString(code),code,name));
                lists.add(treeBean);
            }
        }
        return lists;
    }

    private static List<Map> menuChildMap(List<Map> treeModels,String id,String code,String name) {
        List<Map> lists = new ArrayList<Map>();
        for (Map entity :treeModels) {
            Iterator<String> itr = entity.keySet().iterator();
            JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
            Map treeBean = new HashMap();
            String parentCode = menuJSON.getString("parentCode");
            if (parentCode.equals(id)) {
                treeBean.put("key",menuJSON.getString(code));
                treeBean.put("title",menuJSON.getString(name));
                treeBean.put("value",menuJSON.getString(name));
                treeBean.put("children",menuChildMap(treeModels,menuJSON.getString(code),code,name));
                lists.add(treeBean);
            }
        }
        return lists;
    }

    /**
     * 数据集合转成树形结构
     * @param dataList 数据集合MAP
     * @param code 主键名称
     * @param parent 上级节点
     * @param name 显示名称
     * @param check 节点
     * @return
     */
    public static List<Map> getTreeBeanList(List<Map> dataList, String code, String parent, String name, String check, boolean expandAll){
        List<Map> treeBeanList = new ArrayList<Map>();
        if(oConvertUtils.isNotEmpty(dataList)){
            for(Map entity : dataList){
                Iterator<String> itr = entity.keySet().iterator();
                JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
                if(!checkTreeBeanExist(dataList,entity.get(parent),check)){
                    Map treeBean = new HashMap();
                    while(itr.hasNext()){
                        String key = itr.next();
                        treeBean.put(key, entity.get(key));
                    }
                    treeBean.put("key",menuJSON.getString(code));
                    treeBean.put("title",menuJSON.getString(name));
                    treeBean.put("value",menuJSON.getString(name));
                    parseChildrenMap(treeBean,dataList,entity.get(check),menuJSON.getString(code),code,parent,name,check,expandAll);
                    treeBeanList.add(treeBean);
                }
            }
        }
        return treeBeanList;
    }


    //递归处理下级节点
    private static void parseChildrenMap(Map treeBean,List<Map> datatList,Object unitCode,String parentCode,String code,String parent,String name,String check,boolean expandAll){
        List<Map> treeBeanList = new ArrayList<Map>();
        for(Map entity : datatList){
            Iterator<String> itr = entity.keySet().iterator();
            JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
            if( unitCode.equals(entity.get(parent))){
                Map childBean = new HashMap();
                while(itr.hasNext()){
                    String key = itr.next();
                    childBean.put(key, entity.get(key));
                }
                childBean.put("key",menuJSON.getString(code));
                childBean.put("title",menuJSON.getString(name));
                childBean.put("value",menuJSON.getString(name));
                parseChildrenMap(childBean,datatList,entity.get(check),menuJSON.getString(code),code,parent,name,check,expandAll);//递归查询子节点
                treeBeanList.add(childBean);
            }
        }
        if(treeBeanList != null && treeBeanList.size()>0){
            treeBean.put("children",treeBeanList);
        }

    }

    /**
     * 数据集合转成树形结构
     * @param dataList 数据集合MAP
     * @param code 主键名称
     * @param parent 上级节点
     * @param name 显示名称
     * @param check 节点
     * @return
     */
    public static List<TreeModel> getTreeBeanFromMap(List<Map> dataList, String code, String parent, String name, String check, boolean expandAll){
        List<TreeModel> treeBeanList = new ArrayList<TreeModel>();
        if(oConvertUtils.isNotEmpty(dataList)){
            for(Map entity : dataList){
                JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
                if(!checkTreeBeanExist(dataList,entity.get(parent),check)){
                    TreeModel treeBean = new TreeModel();
                    treeBean.setKey(menuJSON.getString(code));
                    treeBean.setParentId("");

                    if(name.indexOf(",") > -1){
                        //合并显示多个字段(,menuCode,),menuName
//						String realName = "";
                        StringBuffer realName = new StringBuffer("");
                        String[]  showNames = name.split(",");
                        for(String showName : showNames){
                            if(showName.length() == 1){
                                realName.append(showName);
//								realName += showName;//分隔符仅一位
                            }else{
                                String realNames = String.valueOf(entity.get(showName));
                                if(oConvertUtils.isNotEmpty(realNames)){
                                    realName.append(realNames);
//									realName += realNames;
                                }
                            }
                        }
                        treeBean.setTitle(realName.toString());
                        treeBean.setValue(realName.toString());
                    }else{
                        treeBean.setTitle(String.valueOf(entity.get(name)));
                        treeBean.setValue(String.valueOf(entity.get(name)));

                    }

                    Iterator<String> itr = entity.keySet().iterator();
                    parseChildrenMap(treeBean,dataList,entity.get(check),menuJSON.getString(code),code,parent,name,check,expandAll);
                    treeBeanList.add(treeBean);
                }
            }
        }
        return treeBeanList;
    }


    //判断上级节点是否存在,存在返回true
    private static boolean checkTreeBeanExist(List<Map> datatList,Object parentCode,String check){
        for(Map entity : datatList){
            Object checkCode = entity.get(check);
            //System.out.println("检查上级节点是否存在：checkCode="+checkCode+" parentCode="+parentCode);
            if(checkCode != null && checkCode.equals(parentCode)){
                return true;
            }
        }
        return false;
    }


    //递归处理下级节点
    private static void parseChildrenMap(TreeModel treeBean,List<Map> datatList,Object unitCode,String parentCode,String code,String parent,String name,String check,boolean expandAll){
        List<TreeModel> treeBeanList = new ArrayList<TreeModel>();
        for(Map entity : datatList){
            JSONObject menuJSON = (JSONObject) JSON.toJSON(entity);
            if( unitCode.equals(entity.get(parent))){
                TreeModel childBean = new TreeModel();
                childBean.setKey(menuJSON.getString(code));
                childBean.setParentId(parentCode);

                if(name.indexOf(",") > -1){
                    //合并显示多个字段(,menuCode,),menuName
//					String realName = "";
                    StringBuffer realName = new StringBuffer("");
                    String[]  showNames = name.split(",");
                    for(String showName : showNames){
                        if(showName.length() == 1){
                            realName.append(showName);
//							realName += showName;//分隔符仅一位
                        }else{
                            String realNames = String.valueOf(entity.get(showName));
                            if(oConvertUtils.isNotEmpty(realNames)){
                                realName.append(String.valueOf(entity.get(showName)));
//								realName += String.valueOf(entity.get(showName));
                            }
                        }
                    }
                    childBean.setTitle(realName.toString());
                    childBean.setValue(realName.toString());
                }else{
                    childBean.setTitle(String.valueOf(entity.get(name)));
                    childBean.setValue(String.valueOf(entity.get(name)));

                }

                Iterator<String> itr = entity.keySet().iterator();

                parseChildrenMap(childBean,datatList,entity.get(check),menuJSON.getString(code),code,parent,name,check,expandAll);//递归查询子节点
                treeBeanList.add(childBean);
            }
        }
        treeBean.setChildren(treeBeanList);
    }



    public static List<JSONObject> toTreeList(List oldList,String parentCode,String code) {
        int prePid = -1;
        List<JSONObject> tmpList = new ArrayList<>();
        Map<Integer, List<JSONObject>> childMap = new HashMap<>();
        Map<Integer, JSONObject> allMap = new HashMap<>();
        for (Object obj : oldList) {
            JSONObject menuJSON = (JSONObject) JSON.toJSON(obj);

            if (menuJSON.getInteger("pid") != prePid && prePid != -1) {
                if (allMap.containsKey(prePid) && !allMap.get(prePid).containsKey("children")) {
                    allMap.get(prePid).put("children", tmpList);
                } else {
                    childMap.put(prePid, tmpList);
                }
                tmpList = new ArrayList<>();
            }
            if (childMap.containsKey(menuJSON.getInteger("id"))) {
                //菜单有子菜单，安装上
                menuJSON.put("children", childMap.get(menuJSON.getInteger("id")));
                childMap.remove(menuJSON.getInteger("id"));
            }
            prePid = menuJSON.getInteger("pid");
            tmpList.add(menuJSON);
            allMap.put(menuJSON.getInteger("id"), menuJSON);
        }

        if(childMap.size() > 0){
            for(List<JSONObject> child : childMap.values()){
                tmpList.addAll(child);
            }

        }

        return tmpList;
    }

    public static void setChildrenRoleWithParent(List<JSONObject> dataList, Integer parentRole){
        for(JSONObject item : dataList){
            if(parentRole != null) {
                item.put("roleId", parentRole);
            }
            if(item.containsKey("children")){
                setChildrenRoleWithParent((List<JSONObject>)item.get("children"),
                        parentRole!=null?parentRole:item.getInteger("roleId"));
            }
        }
    }
}
