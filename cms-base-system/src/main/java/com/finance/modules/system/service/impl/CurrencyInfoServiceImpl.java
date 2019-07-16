package com.finance.modules.system.service.impl;

import com.finance.modules.system.entity.CurrencyInfo;
import com.finance.modules.system.mapper.CurrencyInfoMapper;
import com.finance.modules.system.service.ICurrencyInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 币别维护
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Service
public class CurrencyInfoServiceImpl extends ServiceImpl<CurrencyInfoMapper, CurrencyInfo> implements ICurrencyInfoService {

}
