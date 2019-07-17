package com.finance.modules.system.service.impl;

import com.finance.modules.system.entity.TraceInfo;
import com.finance.modules.system.mapper.TraceInfoMapper;
import com.finance.modules.system.service.ITraceInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 系统日志
 * @Author: tangc
 * @Date:   2019-07-15
 * @Version: V1.0
 */
@Service
public class TraceInfoServiceImpl extends ServiceImpl<TraceInfoMapper, TraceInfo> implements ITraceInfoService {

}
