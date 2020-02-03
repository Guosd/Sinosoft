package com.sinosoft.nimg.persistence.sysuser.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.nimg.persistence.sysuser.po.PrpDUserRole;
import com.github.framework.evo.base.dao.BaseXmlDao;

/**
 *
 * 表PRP_D_USER_ROLE对应的基于MyBatis实现的Dao接口<br/>
 * 在其中添加自定义方法
 *
 */
@Mapper
public interface PrpDUserRoleDao extends BaseXmlDao<PrpDUserRole, String> {

}
