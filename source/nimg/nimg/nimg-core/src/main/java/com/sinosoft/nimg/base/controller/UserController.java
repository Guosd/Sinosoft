package com.sinosoft.nimg.base.controller;

import com.github.framework.evo.common.model.ResponseVo;
import com.sinosoft.nimg.base.service.UserService;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
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
}
