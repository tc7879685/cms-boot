package com.finance.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.common.util.oConvertUtils;
import com.finance.modules.system.entity.RoleMenu;
import com.finance.modules.system.mapper.RoleMenuMapper;
import com.finance.modules.system.service.IRoleMenuServce;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper,RoleMenu> implements IRoleMenuServce {

    @Override
    public void saveRoleMenu(String corpCode, String roleCode, String permissionIds, String lastPermissionIds) {
        List<String> add = getDiff(lastPermissionIds,permissionIds);
        if(add!=null && add.size()>0) {
            List<RoleMenu> list = new ArrayList<RoleMenu>();
            for (String menuCode : add) {
                if(oConvertUtils.isNotEmpty(menuCode)) {
                    RoleMenu rolepms = new RoleMenu();
                    rolepms.setCorpCode(corpCode);
                    rolepms.setMenuCode(menuCode);
                    rolepms.setRoleCode(roleCode);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }

        List<String> delete = getDiff(permissionIds,lastPermissionIds);
        if(delete!=null && delete.size()>0) {
            for (String menuCode : delete) {
                this.remove(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getCorpCode,corpCode).eq(RoleMenu::getRoleCode, roleCode).eq(RoleMenu::getMenuCode, menuCode));
            }
        }
    }


    /**
     * 从diff中找出main中没有的元素
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main,String diff){
        if(oConvertUtils.isEmpty(diff)) {
            return null;
        }
        if(oConvertUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }

        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if(oConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }
}
