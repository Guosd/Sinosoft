package com.sinosoft.nimg.persistence.sysuser.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.nimg.persistence.sysuser.po.PrpDUser;
import com.github.framework.evo.base.dao.BaseXmlDao;

import java.util.List;

/**
 *
 * 表PRP_D_USER对应的基于MyBatis实现的Dao接口<br/>
 * 在其中添加自定义方法
 *
 */
@Mapper
public interface PrpDUserDao extends BaseXmlDao<PrpDUser, String> {

    List<String> permission(String userCode);
}
