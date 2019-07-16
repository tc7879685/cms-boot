package com.finance.modules.system.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 用途维护
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_usage_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UsageInfo {

	@Id
	private Integer usageUUID;
	private String corpCode;
	private String usageCode;
	private String usageName;
	private String usageMemo;
	private Integer showIndex;
	private Integer holdFlag;
	private Integer statusCode;
}
