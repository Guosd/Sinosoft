package com.sinosoft.nimg.persistence.sysconfig.po;

import com.github.framework.evo.base.entity.BaseXmlEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 通过evo-code-generate-mybatis工具自动生成，请勿手工修改。表PRP_D_CODE的PO对象<br/>
 * 对应表名：PRP_D_CODE,备注：系统转码表
 *
 */
@Data
public class PrpDCodePage extends PrpDCode implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	private String pageNo;
	/** 对应字段：CODE_TYPE,备注：代码类型 */
	private String pageSize;

}
