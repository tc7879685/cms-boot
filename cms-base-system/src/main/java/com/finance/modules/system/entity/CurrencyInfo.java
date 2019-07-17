package com.finance.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 币别维护
 * @Author: tangc
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_currency_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CurrencyInfo {

	@TableId
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
