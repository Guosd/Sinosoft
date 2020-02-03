package com.sinosoft.nimg.persistence.sysconfig.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.nimg.persistence.sysconfig.po.PrpDCode;
import com.github.framework.evo.base.dao.BaseXmlDao;

/**
 *
 * 表PRP_D_CODE对应的基于MyBatis实现的Dao接口<br/>
 * 在其中添加自定义方法
 *
 */
@Mapper
public interface PrpDCodeDao extends BaseXmlDao<PrpDCode, String> {

}
