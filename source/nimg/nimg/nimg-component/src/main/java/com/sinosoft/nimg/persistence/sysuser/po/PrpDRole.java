package com.sinosoft.nimg.persistence.sysuser.po;

import com.github.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表PRP_D_ROLE的PO对象<br/>
 * 对应表名：PRP_D_ROLE,备注：用户角色表
 *
 */
@Data
public class PrpDRole extends BaseXmlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	private String id;
	/** 对应字段：ROLE_CODE,备注：角色代码 */
	private String roleCode;
	/** 对应字段：ROLE_NAME,备注：角色名称 */
	private String roleName;
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
