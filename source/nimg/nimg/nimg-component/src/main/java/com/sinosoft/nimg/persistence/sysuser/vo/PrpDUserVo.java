package com.sinosoft.nimg.persistence.sysuser.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * PrpDUserVo对象.对应实体描述：用户信息表
 *
 */
@Data
@ApiModel("PrpDUserVo对象")
public class PrpDUserVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	@ApiModelProperty("主键")
	private String id;
	/** 对应字段：USER_CODE,备注：用户代码 */
	@ApiModelProperty("用户代码")
	private String userCode;
	/** 对应字段：USER_C_NAME,备注：用户名称 */
	@ApiModelProperty("用户名称")
	private String userCName;
	/** 对应字段：COM_CODE,备注：机构代码 */
	@ApiModelProperty("机构代码")
	private String comCode;
	/** 对应字段：PASSWORD,备注：密码 */
	@ApiModelProperty("密码")
	private String password;
	/** 对应字段：KEY,备注：aes加密key */
	@ApiModelProperty("aes加密key")
	private String key;
	/** 对应字段：IV,备注：aes加密偏移量 */
	@ApiModelProperty("aes加密偏移量")
	private String iv;
	/** 对应字段：SYSTEM,备注：系统代码 */
	@ApiModelProperty("系统代码")
	private String system;
}
