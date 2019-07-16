package com.finance.modules.system.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 系统参数维护
 * @Author: jeecg-boot
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_define_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DefineInfo {
    
	private String corpCode;
	private String defineCode;
	private Integer defineType;
	private String defineName;
	private String defineValue;
	private String defineMemo;
	private Integer showIndex;
	private Integer holdFlag;
	private Integer statusCode;
}
