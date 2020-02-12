package com.sinosoft.nimg.persistence.sysuser.vo;

import lombok.Data;

import java.util.List;

@Data
public class TaskTreeSaveVo {
    // 角色代码
    private String roleCode;

    // 任务信息
    private List<String> taskIds;
}
