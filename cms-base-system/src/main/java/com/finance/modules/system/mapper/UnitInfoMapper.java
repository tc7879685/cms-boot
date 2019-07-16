package com.finance.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finance.modules.system.entity.UnitInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UnitInfoMapper extends BaseMapper<UnitInfo> {


    /**
     * 查询单位列表
     * @param unitInfo
     * @return
     */
    List<Map> queryUnitList(UnitInfo unitInfo);
}
