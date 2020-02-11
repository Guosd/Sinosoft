package com.sinosoft.nimg.base.service;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.base.util.PageHelper;
import com.github.framework.evo.common.model.ResponseVo;
import com.github.framework.evo.common.uitl.BeanCopyUtils;
import com.sinosoft.nimg.persistence.sysconfig.po.PrpDCode;
import com.sinosoft.nimg.persistence.sysconfig.vo.PrpDCodeVo;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDRoleDao;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDTaskDao;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDUserDao;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDRole;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDTask;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDUser;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDRoleVo;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDTaskVo;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PrpDUserDao prpDUserDao;
    @Autowired
    private PrpDRoleDao prpDRoleDao;
    @Autowired
    private PrpDTaskDao prpDTaskDao;

    public PrpDUserVo login(PrpDUserVo prpDUserVo) {
        PrpDUser prpDUser = prpDUserDao.getOne(prpDUserVo);
        return BeanCopyUtils.clone(prpDUser,PrpDUserVo.class);
    }

    public int userAdd(PrpDUserVo prpDUserVo){
        return prpDUserDao.insertSelective(BeanCopyUtils.clone(prpDUserVo,PrpDUser.class));
    }

    public List<String> permission() {
        //todo 这里需要从session 中获取用户信息的！
        List<String> list = prpDUserDao.permission("000000");
        return list;
    }

    /**
     * 分页查询角色
     * @param prpDRoleVo
     * @return
     */
    public PageResult<PrpDRoleVo> queryForPage(PrpDRoleVo prpDRoleVo) {
        PageParam pageParam = PageHelper.getPageParam(prpDRoleVo);
        Page<PrpDRole> prpDRolePage = prpDRoleDao.query(pageParam, BeanCopyUtils.clone(prpDRoleVo, PrpDRole.class));
        PageResult<PrpDRoleVo> result = PageHelper.convert(prpDRolePage, PrpDRoleVo.class);
        return result;
    }

    /**
     * 查询所有的Task
     * @return
     */
    public List<PrpDTaskVo> queryTask() {
        List<PrpDTask> list = prpDTaskDao.find(null);
        return BeanCopyUtils.cloneList(list, PrpDTaskVo.class);
    }
}
