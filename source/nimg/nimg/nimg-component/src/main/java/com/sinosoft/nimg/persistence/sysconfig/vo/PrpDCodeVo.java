package com.sinosoft.nimg.persistence.sysconfig.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * PrpDCodeVo对象.对应实体描述：系统转码表
 *
 */
@Data
@ApiModel("PrpDCodeVo对象")
public class PrpDCodeVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：ID,备注：主键 */
	@ApiModelProperty("主键")
	private String id;
	/** 对应字段：CODE_TYPE,备注：代码类型 */
	@ApiModelProperty("代码类型")
	private String codeType;
	/** 对应字段：CODE_CODE,备注：代码 */
	@ApiModelProperty("代码")
	private String codeCode;
	/** 对应字段：CODE_C_NAME,备注：中文名称 */
	@ApiModelProperty("中文名称")
	private String codeCName;
	/** 对应字段：CODE_E_NAME,备注：英文名称 */
	@ApiModelProperty("英文名称")
	private String codeEName;
	/** 对应字段：CODE_T_NAME,备注：繁体名称 */
	@ApiModelProperty("繁体名称")
	private String codeTName;
	/** 对应字段：DISPLAY_NO,备注：显示序号 */
	@ApiModelProperty("显示序号")
	private BigDecimal displayNo;
	/** 对应字段：DISPLAY_NO,备注：显示序号 */
	@ApiModelProperty("转码类型  E:英文  C:中文 T:繁体")
	private String transType;
}
