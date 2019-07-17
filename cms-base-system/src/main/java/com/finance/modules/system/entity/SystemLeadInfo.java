package com.finance.modules.system.entity;



import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @Description: 系统引导
 * @Author: tangc
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_lead_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemLeadInfo {

	@TableId
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
