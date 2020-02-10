package com.sinosoft.nimg.persistence.sysconfig.po;

import com.github.framework.evo.base.entity.BaseXmlEntity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表PRP_D_CODE的PO对象<br/>
 * 对应表名：PRP_D_CODE,备注：系统转码表
 *
 */
@Data
public class PrpDCode extends BaseXmlEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	private String id;
	/** 对应字段：CODE_TYPE,备注：代码类型 */
	private String codeType;
	/** 对应字段：CODE_CODE,备注：代码 */
	private String codeCode;
	/** 对应字段：CODE_C_NAME,备注：中文名称 */
	private String codeCName;
	/** 对应字段：CODE_E_NAME,备注：英文名称 */
	private String codeEName;
	/** 对应字段：CODE_T_NAME,备注：繁体名称 */
	private String codeTName;
	/** 对应字段：DISPLAY_NO,备注：显示序号 */
	private BigDecimal displayNo;
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
