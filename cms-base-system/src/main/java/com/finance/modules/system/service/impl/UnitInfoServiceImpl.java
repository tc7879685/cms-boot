package com.finance.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.modules.system.entity.UnitInfo;
import com.finance.modules.system.mapper.UnitInfoMapper;
import com.finance.modules.system.service.IUnitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UnitInfoServiceImpl extends ServiceImpl<UnitInfoMapper, UnitInfo> implements IUnitInfoService {

    @Autowired
    UnitInfoMapper unitInfoMapper;

    @Override
    public List<Map> queryUnitList(UnitInfo unitInfo) {
        return unitInfoMapper.queryUnitList(unitInfo);
    }
}
