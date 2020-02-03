package com.sinosoft.nimg.persistence.sysuser.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * PrpDRoleTaskVo对象.对应实体描述：角色任务关联表
 *
 */
@Data
@ApiModel("PrpDRoleTaskVo对象")
public class PrpDRoleTaskVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	@ApiModelProperty("主键")
	private String id;
	/** 对应字段：TASK_ID,备注：任务主键 */
	@ApiModelProperty("任务主键")
	private String taskId;
	/** 对应字段：ROLE_ID,备注：角色主键 */
	@ApiModelProperty("角色主键")
	private String roleId;
	/** 对应字段：DELETE_FLAG,备注：删除标志 */
	@ApiModelProperty("删除标志")
	private String deleteFlag;
	/** 对应字段：CREATE_BY,备注：创建者 */
	@ApiModelProperty("创建者")
	private String createBy;
	/** 对应字段：UPDATE_BY,备注：更新者 */
	@ApiModelProperty("更新者")
	private String updateBy;
	/** 对应字段：CREATE_TIME,备注：创建时间 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	/** 对应字段：UPDATE_TIME,备注：更新时间 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
}
