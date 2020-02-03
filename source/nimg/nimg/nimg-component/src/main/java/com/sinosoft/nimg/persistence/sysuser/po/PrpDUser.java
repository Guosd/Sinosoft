package com.sinosoft.nimg.persistence.sysuser.po;

import com.github.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表PRP_D_USER的PO对象<br/>
 * 对应表名：PRP_D_USER,备注：用户信息表
 *
 */
@Data
public class PrpDUser extends BaseXmlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	private String id;
	/** 对应字段：USER_CODE,备注：用户代码 */
	private String userCode;
	/** 对应字段：USER_C_NAME,备注：用户名称 */
	private String userCName;
	/** 对应字段：COM_CODE,备注：机构代码 */
	private String comCode;
	/** 对应字段：PASSWORD,备注：密码 */
	private String password;
	/** 对应字段：KEY,备注：aes加密key */
	private String key;
	/** 对应字段：IV,备注：aes加密偏移量 */
	private String iv;
	/** 对应字段：DELETE_FLAG,备注：删除标志 */
	private String deleteFlag;
	/** 对应字段：SYSTEM,备注：系统代码 */
	private String system;
	/** 对应字段：CREATE_BY,备注：创建者 */
	private String createBy;
	/** 对应字段：UPDATE_BY,备注：更新者 */
	private String updateBy;
	/** 对应字段：CREATE_TIME,备注：创建时间 */
	private Date createTime;
	/** 对应字段：UPDATE_TIME,备注：更新时间 */
	private Date updateTime;
}
