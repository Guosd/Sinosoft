package com.sinosoft.nimg.persistence.sysuser.po;

import com.github.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表PRP_D_TASK的PO对象<br/>
 * 对应表名：PRP_D_TASK,备注：用户任务表
 *
 */
@Data
public class PrpDTask extends BaseXmlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	private String id;
	/** 对应字段：TASK_CODE,备注：任务代码 */
	private String taskCode;
	/** 对应字段：PARENT_CODE,备注：父任务代码 */
	private String parentCode;
	/** 对应字段：TASK_NAME,备注：任务名称 */
	private String taskName;
	/** 对应字段：URL,备注：任务链接 */
	private String url;
	/** 对应字段：SYSTEM,备注：系统代码 */
	private String system;
	/** 对应字段：DELETE_FLAG,备注：删除标志 */
	private String deleteFlag;
	/** 对应字段：CREATE_BY,备注：创建者 */
	private String createBy;
	/** 对应字段：UPDATE_BY,备注：更新者 */
	private String updateBy;
	/** 对应字段：CREATE_TIME,备注：创建时间 */
	private Date createTime;
	/** 对应字段：UPDATE_TIME,备注：更新时间 */
	private Date updateTime;
}
