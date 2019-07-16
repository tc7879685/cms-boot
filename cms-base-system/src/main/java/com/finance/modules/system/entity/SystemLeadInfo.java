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
 * @Description: 系统引导
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_lead_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemLeadInfo {

	@Id
	private Integer leadUUID;
	private String  leadTitle;
	private Object  leadContent;
	private String  leadSign;
	private String  dataSource;
	private String  corpCode;
	private String  menuCode;
	private Integer isMain;
	private Integer isInit;
	private Integer isSelf;
}
