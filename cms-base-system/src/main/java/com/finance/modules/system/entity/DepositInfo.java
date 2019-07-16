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
 * @Description: 联行号
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_deposit_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DepositInfo {
    @Id
	private String depositCode;
	private String depositName;
	private String cityCode;
	private String bankCode;
	private String bankType;
	private Integer statusCode;
}
