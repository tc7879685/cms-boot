package com.finance.modules.system.mapper;

import com.finance.model.TableModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysBaseAPIMapper {

    Integer getMaxId(TableModel tableModel);
}
