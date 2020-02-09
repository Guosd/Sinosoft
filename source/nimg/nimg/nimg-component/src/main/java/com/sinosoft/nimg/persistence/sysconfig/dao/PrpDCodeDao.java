package com.sinosoft.nimg.persistence.sysconfig.dao;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.base.dao.BaseXmlDao;
import com.sinosoft.nimg.persistence.sysconfig.po.PrpDCode;
import com.sinosoft.nimg.persistence.sysconfig.vo.PrpDCodeVo;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * 表PRP_D_CODE对应的基于MyBatis实现的Dao接口<br/>
 * 在其中添加自定义方法
 *
 */
@Mapper
public interface PrpDCodeDao extends BaseXmlDao<PrpDCode, String> {

    Page<PrpDCodeVo> query(PageParam pageParam, PrpDCode clone);
}
