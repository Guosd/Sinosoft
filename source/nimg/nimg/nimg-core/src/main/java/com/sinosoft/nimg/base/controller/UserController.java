package com.sinosoft.nimg.base.controller;

import com.sinosoft.nimg.base.service.UserService;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDUser;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
