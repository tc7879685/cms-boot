package com.finance.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 存储的Logo
 * @Author: tangc
 * @Date:   2019-07-16
 * @Version: V1.0
 */
@Data
@TableName("sys_logo_upload")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogoUpload {
    @TableId
	private Integer logoId;
	private String  corpCode;
	private String  userCode;
	private Object  logoImg;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date    uploadTime;
	private Integer status;
}
