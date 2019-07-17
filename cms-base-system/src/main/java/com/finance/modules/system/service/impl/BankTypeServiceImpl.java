package com.finance.modules.system.service.impl;

import com.finance.modules.system.entity.BankType;
import com.finance.modules.system.mapper.BankTypeMapper;
import com.finance.modules.system.service.IBankTypeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 银行类别维护
 * @Author: tangc
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Service
public class BankTypeServiceImpl extends ServiceImpl<BankTypeMapper, BankType> implements IBankTypeService {

}
