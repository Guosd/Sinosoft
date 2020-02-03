package com.sinosoft.nimg.persistence.sysuser.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * PrpDUserRoleVo对象.对应实体描述：用户角色关联表
 *
 */
@Data
@ApiModel("PrpDUserRoleVo对象")
public class PrpDUserRoleVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	@ApiModelProperty("主键")
	private String id;
	/** 对应字段：USER_ID,备注：用户主键 */
	@ApiModelProperty("用户主键")
	private String userId;
	/** 对应字段：ROLE_ID,备注：角色主键 */
	@ApiModelProperty("角色主键")
	private String roleId;
}
