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
 * @Description: 币别维护
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_currency_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CurrencyInfo {

	@Id
	private Integer currencyUUID;
	private String corpCode;
	private String currencyCode;
	private String englishName;
	private String chineseName;
	private String depositCode;
	private Integer standardFlag;
	private String currencyMemo;
	private Integer showIndex;
	private Integer holdFlag;
	private Integer statusCode;
}
