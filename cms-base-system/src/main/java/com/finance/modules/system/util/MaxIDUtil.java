package com.finance.modules.system.util;

import com.finance.common.system.api.ISysBaseAPI;
import com.finance.common.util.RedisUtil;
import com.finance.common.util.SpringContextUtils;
import com.finance.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取表格最大ID
 */
@Component
public class MaxIDUtil {

    @Autowired
    private  RedisUtil redisUtil;

    public   Integer getMaxId(TableModel tableModel){
        Object obj =  redisUtil.get("MAX_ID_"+tableModel.getTableName()+"_"+tableModel.getFiled());
        if(obj != null){
            return  (Integer) obj;
        }else{
            ISysBaseAPI iSysBaseAPI =  SpringContextUtils.getBean(ISysBaseAPI.class);
            return iSysBaseAPI.getMaxID(tableModel);
        }

    }
}
