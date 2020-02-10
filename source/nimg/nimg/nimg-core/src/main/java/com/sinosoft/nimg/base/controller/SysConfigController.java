package com.sinosoft.nimg.base.controller;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.common.model.ResponseVo;
import com.sinosoft.nimg.base.service.SysConfigService;
import com.sinosoft.nimg.persistence.sysconfig.vo.PrpDCodeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sysconfig")
@CrossOrigin
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 查询系统页面的翻译
     * @param prpDCodeVo 转码类型 E: 英文，
     * @return
     */
    @ApiOperation(value = "查询页面翻译数据")
    @PostMapping("/translate")
    public ResponseVo<Map<String,String>> selectTranslate(@RequestBody PrpDCodeVo prpDCodeVo){
        return ResponseVo.ok(sysConfigService.selectTranslate(prpDCodeVo));
    }

    /**
     * 分页查询配置表
     * @return
     */
    @ApiOperation(value = "分页查询配置表")
    @PostMapping("/queryForPage")
    public ResponseVo<PageResult<PrpDCodeVo>> queryForPage(@RequestBody PrpDCodeVo prpDCodeVo){
        return ResponseVo.ok(sysConfigService.queryForPage(prpDCodeVo));
    }

    /**
     * 更改系统代码
     * @param prpDCodeVo 转码类型 E: 英文，
     * @return
     */
    @ApiOperation(value = "更改系统代码")
    @PostMapping("/updateSysCode")
    public ResponseVo<Integer> updateSysCode(@RequestBody PrpDCodeVo prpDCodeVo){
        return ResponseVo.ok(sysConfigService.updateSysCode(prpDCodeVo));
    }

    /**
     * 删除系统代码
     * @param prpDCodeVo 转码类型 E: 英文，
     * @return
     */
    @ApiOperation(value = "删除系统代码")
    @PostMapping("/deleteSysCode")
    public ResponseVo<Integer> deleteSysCode(@RequestBody PrpDCodeVo prpDCodeVo){
        return ResponseVo.ok(sysConfigService.deleteSysCode(prpDCodeVo));
    }

    /**
     * 保存系统代码
     * @param prpDCodeVo 转码类型 E: 英文，
     * @return
     */
    @ApiOperation(value = "保存系统代码")
    @PostMapping("/saveSysCode")
    public ResponseVo<Integer> saveSysCode(@RequestBody PrpDCodeVo prpDCodeVo){
        return ResponseVo.ok(sysConfigService.saveSysCode(prpDCodeVo));
    }
}
