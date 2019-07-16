package com.finance.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * @Description: 银行类别维护
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_bank_type")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BankType {

	@Id
	private Integer typeUUID;
	private String corpCode;
	private Integer bankType;
	private String typeCode;
	private String typeName;
	private String typeMemo;
	private Integer showIndex;
	private Integer holdFlag;
	private Integer statusCode;
}
