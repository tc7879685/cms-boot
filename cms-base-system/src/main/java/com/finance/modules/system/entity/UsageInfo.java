package com.finance.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @Description: 用途维护
 * @Author: tangc
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_usage_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UsageInfo {

	@TableId
	private Integer usageUUID;
	private String corpCode;
	private String usageCode;
	private String usageName;
	private String usageMemo;
	private Integer showIndex;
	private Integer holdFlag;
	private Integer statusCode;
}
