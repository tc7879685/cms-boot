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
 * @Description: 系统参数维护
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_busi_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusiInfo {

	@Id
	private Integer busiUUID;
	private String corpCode;
	private Integer busiType;
	private String busiCode;
	private String busiName;
	private String parentCode;
	private String busiMemo;
	private Integer showIndex;
	private Integer paymentFlag;
	private Integer holdFlag;
	private Integer statusCode;
	private Integer showFlag;
	private Integer flowFlag;
}
