package com.finance.modules.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 系统日志
 * @Author: tangc
 * @Date:   2019-07-15
 * @Version: V1.0
 */
@Data
@TableName("sys_trace_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TraceInfo {

    @TableId(type = IdType.UUID)
	private String traceUUID;
	private String corpCode;
	private String userCode;
	private String menuCode;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date operateTime;
	private Integer operateType;
	private String operateInfo;

	public  TraceInfo(){}

	public TraceInfo(String corpCode,String userCode,String operateInfo,Integer operateType){
		this.corpCode = corpCode;
		this.userCode = userCode;
		this.operateInfo = operateInfo;
		this.operateType = operateType;
	}
}
