package com.sinosoft.nimg.base.service;

import com.github.framework.evo.common.model.ResponseVo;
import com.github.framework.evo.common.uitl.BeanCopyUtils;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDUserDao;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDUser;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PrpDUserDao prpDUserDao;

    public PrpDUserVo login(PrpDUserVo prpDUserVo) {
        PrpDUser prpDUser = prpDUserDao.getOne(prpDUserVo);
        return BeanCopyUtils.clone(prpDUser,PrpDUserVo.class);
    }

    public int userAdd(PrpDUserVo prpDUserVo){
        return prpDUserDao.insertSelective(BeanCopyUtils.clone(prpDUserVo,PrpDUser.class));
    }

    public List<String> permission() {
        List<String> list = prpDUserDao.permission("000000");
        list.forEach(System.out::println);
        return list;
    }
}
