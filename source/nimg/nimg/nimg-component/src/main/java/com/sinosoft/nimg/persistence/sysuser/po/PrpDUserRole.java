package com.sinosoft.nimg.persistence.sysuser.po;

import com.github.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表PRP_D_USER_ROLE的PO对象<br/>
 * 对应表名：PRP_D_USER_ROLE,备注：用户角色关联表
 *
 */
@Data
public class PrpDUserRole extends BaseXmlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	private String id;
	/** 对应字段：USER_ID,备注：用户主键 */
	private String userId;
	/** 对应字段：ROLE_ID,备注：角色主键 */
	private String roleId;
	/** 对应字段：DELETED_FLAG,备注：删除标志 */
	private String deletedFlag;
	/** 对应字段：CREATE_BY,备注：创建者 */
	private String createBy;
	/** 对应字段：UPDATE_BY,备注：更新者 */
	private String updateBy;
	/** 对应字段：CREATE_TIME,备注：创建时间 */
	private Date createTime;
	/** 对应字段：UPDATE_TIME,备注：更新时间 */
	private Date updateTime;
}
