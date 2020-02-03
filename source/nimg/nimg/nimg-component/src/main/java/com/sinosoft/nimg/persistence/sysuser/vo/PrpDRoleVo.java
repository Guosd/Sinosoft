package com.sinosoft.nimg.persistence.sysuser.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * PrpDRoleVo对象.对应实体描述：用户角色表
 *
 */
@Data
@ApiModel("PrpDRoleVo对象")
public class PrpDRoleVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	@ApiModelProperty("主键")
	private String id;
	/** 对应字段：ROLE_CODE,备注：角色代码 */
	@ApiModelProperty("角色代码")
	private String roleCode;
	/** 对应字段：ROLE_NAME,备注：角色名称 */
	@ApiModelProperty("角色名称")
	private String roleName;
	/** 对应字段：SYSTEM,备注：系统代码 */
	@ApiModelProperty("系统代码")
	private String system;
}
