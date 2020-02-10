package com.sinosoft.nimg.base.service;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.base.util.PageHelper;
import com.github.framework.evo.common.uitl.BeanCopyUtils;
import com.sinosoft.nimg.base.constrant.SysConfig;
import com.sinosoft.nimg.persistence.sysconfig.dao.PrpDCodeDao;
import com.sinosoft.nimg.persistence.sysconfig.po.PrpDCode;
import com.sinosoft.nimg.persistence.sysconfig.vo.PrpDCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysConfigService {
    @Autowired
    private PrpDCodeDao prpDCodeDao;

    /**
     * 查询页面翻译
     * @param prpDCodeVo
     * @return
     */
    public Map<String,String> selectTranslate(PrpDCodeVo prpDCodeVo){
        List<PrpDCode> list = prpDCodeDao.find(BeanCopyUtils.clone(prpDCodeVo,PrpDCode.class));
        Map<String,String> result;
        switch (prpDCodeVo.getTransType()== null ? SysConfig.CHINESE_FONT : prpDCodeVo.getTransType()){
            case SysConfig.ENGLISH_FONT:
                result = list.stream().collect(Collectors.toMap(PrpDCode::getCodeCode,PrpDCode::getCodeEName));
                break;
            case SysConfig.COMPLEX_FONT:
                result = list.stream().collect(Collectors.toMap(PrpDCode::getCodeCode,PrpDCode::getCodeTName));
                break;
            default:
                result = list.stream().collect(Collectors.toMap(PrpDCode::getCodeCode,PrpDCode::getCodeCName));
        }

        return result;
    }

    public PageResult<PrpDCodeVo> queryForPage(PrpDCodeVo prpDCodeVo) {
        PageParam pageParam = PageHelper.getPageParam(prpDCodeVo);
        Page<PrpDCode> gfbankaccountPage = prpDCodeDao.query(pageParam, BeanCopyUtils.clone(prpDCodeVo, PrpDCode.class));
        PageResult<PrpDCodeVo> result = PageHelper.convert(gfbankaccountPage, PrpDCodeVo.class);
        return result;
    }

    /**
     * 保存系统代码
     * @param prpDCodeVo
     * @return
     */
    public Integer updateSysCode(PrpDCodeVo prpDCodeVo) {
        return prpDCodeDao.updateSelective(BeanCopyUtils.clone(prpDCodeVo,PrpDCode.class));
    }

    public Integer deleteSysCode(PrpDCodeVo prpDCodeVo) {
        return prpDCodeDao.delete(prpDCodeVo.getId());
    }

    public Integer saveSysCode(PrpDCodeVo prpDCodeVo) {
        return 1;
    }

}
