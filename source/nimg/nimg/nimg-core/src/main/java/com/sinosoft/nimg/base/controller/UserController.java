package com.sinosoft.nimg.base.controller;

import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.common.model.ResponseVo;
import com.sinosoft.nimg.base.service.UserService;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDRoleVo;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
import com.sinosoft.nimg.persistence.sysuser.vo.TaskTreeQueryVo;
import com.sinosoft.nimg.persistence.sysuser.vo.TaskTreeSaveVo;
import feign.RequestLine;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "查询树状结构")
    @PostMapping("/queryTaskRoleRelated")
    public ResponseVo<List<TaskTreeQueryVo>> queryTaskRoleRelated(@RequestBody String roleId){
        return ResponseVo.ok(userService.queryTaskRoleRelated(roleId));
    }

    /**
     * 查询taskCode
     */
    @ApiOperation(value = "保存关联关系")
    @PostMapping("/saveTaskRoleRelated")
    public ResponseVo<Integer> saveTaskRoleRelated(@RequestBody TaskTreeSaveVo taskTreeSaveVo){
        return ResponseVo.ok(userService.saveTaskRoleRelated(taskTreeSaveVo));
    }
}
