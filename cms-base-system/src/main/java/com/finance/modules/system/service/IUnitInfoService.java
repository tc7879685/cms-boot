package com.finance.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.modules.system.entity.UnitInfo;

import java.util.List;
import java.util.Map;

public interface IUnitInfoService extends IService<UnitInfo> {


    /**
     * 查询单位列表
     * @param unitInfo
     * @return
     */
    List<Map> queryUnitList(UnitInfo unitInfo);
}
