package com.finance.modules.system.service.impl;

import com.finance.modules.system.entity.UsageInfo;
import com.finance.modules.system.mapper.UsageInfoMapper;
import com.finance.modules.system.service.IUsageInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用途维护
 * @Author: tangc
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Service
public class UsageInfoServiceImpl extends ServiceImpl<UsageInfoMapper, UsageInfo> implements IUsageInfoService {

}
