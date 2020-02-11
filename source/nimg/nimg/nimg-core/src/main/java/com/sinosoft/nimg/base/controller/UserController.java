package com.sinosoft.nimg.base.controller;

import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.common.model.ResponseVo;
import com.sinosoft.nimg.base.service.UserService;
import com.sinosoft.nimg.persistence.sysconfig.po.PrpDCode;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDTask;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDRoleVo;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDTaskVo;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public PrpDUserVo login(@RequestBody PrpDUserVo prpDUserVo){
        return userService.login(prpDUserVo);
    }

    @PostMapping("/add")
    public Integer userAdd(@RequestBody PrpDUserVo prpDUserVo){
        return userService.userAdd(prpDUserVo);
    }

    @PostMapping("/permission")
    public ResponseVo<List<String>> permission(){
        return ResponseVo.ok(userService.permission());
    }

    /**
     * 分页查询配置表
     * @return
     */
    @ApiOperation(value = "分页查询配置表")
    @PostMapping("/queryForPage")
    public ResponseVo<PageResult<PrpDRoleVo>> queryForPage(@RequestBody PrpDRoleVo prpDCodeVo){
        return ResponseVo.ok(userService.queryForPage(prpDCodeVo));
    }

    /**
     * 查询taskCode
     */
    @ApiOperation(value = "查询所有TaskCode")
    @PostMapping("/taskQuery")
    public ResponseVo<List<PrpDTaskVo>> queryTask(){
        return ResponseVo.ok(userService.queryTask());
    }
}
