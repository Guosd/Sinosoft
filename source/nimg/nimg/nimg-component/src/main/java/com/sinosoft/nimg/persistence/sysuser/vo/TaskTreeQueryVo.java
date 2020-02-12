package com.sinosoft.nimg.persistence.sysuser.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskTreeQueryVo {
    // 唯一标示
    private String id;
    // 标题
    private String title;
    // 是否展开
    private Boolean expand;

    // 是否选中子节点
    private Boolean selected;
    // 是否勾选(如果勾选，子节点也会全部勾选)
    private Boolean checked;
    // 禁掉响应
    private Boolean disabled;
    // 禁掉checkbox
    private Boolean disableCheckbox;

    // 子节点
    private List<TaskTreeQueryVo> children = new ArrayList<>();
}
