package com.sinosoft.nimg.base.service;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.base.util.PageHelper;
import com.github.framework.evo.common.uitl.BeanCopyUtils;
import com.github.framework.evo.common.uitl.StringUtil;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDRoleDao;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDRoleTaskDao;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDTaskDao;
import com.sinosoft.nimg.persistence.sysuser.dao.PrpDUserDao;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDRole;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDRoleTask;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDTask;
import com.sinosoft.nimg.persistence.sysuser.po.PrpDUser;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDRoleVo;
import com.sinosoft.nimg.persistence.sysuser.vo.PrpDUserVo;
import com.sinosoft.nimg.persistence.sysuser.vo.TaskTreeQueryVo;
import com.sinosoft.nimg.persistence.sysuser.vo.TaskTreeSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private PrpDUserDao prpDUserDao;
    @Autowired
    private PrpDRoleDao prpDRoleDao;
    @Autowired
    private PrpDTaskDao prpDTaskDao;
    @Autowired
    private PrpDRoleTaskDao prpDRoleTaskDao;

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
    public List<TaskTreeQueryVo> queryTaskRoleRelated(String roleId) {
        List<PrpDTask> list = prpDTaskDao.find(null);
        PrpDRoleTask prpDRoleTask = new PrpDRoleTask();
        prpDRoleTask.setRoleId(roleId);
        List<PrpDRoleTask> related = prpDRoleTaskDao.find(prpDRoleTask);
        Map<String,String> map = related.stream().collect(Collectors.toMap(PrpDRoleTask::getTaskId,PrpDRoleTask::getRoleId));

        List<TaskTreeQueryVo> result = new ArrayList<>();
        handleTaskTree(map, list, result, "root");
        return result;
    }

    /**
     * 处理TreeList
     * @param list
     * @return
     */
    private boolean handleTaskTree(Map<String,String> map, List<PrpDTask> list, List<TaskTreeQueryVo> result, String parentCode) {
        boolean flag = false;
        for (PrpDTask prpDTask : list) {
            if(prpDTask.getParentCode().equals(parentCode)){
                TaskTreeQueryVo taskTreeQueryVo = new TaskTreeQueryVo();
                taskTreeQueryVo.setId(prpDTask.getId());
                taskTreeQueryVo.setTitle(prpDTask.getTaskName());
                if(StringUtil.isNotBlank(map.get(taskTreeQueryVo.getId()))){
                    taskTreeQueryVo.setChecked(true);
                }
                result.add(taskTreeQueryVo);
                if(handleTaskTree(map,list, taskTreeQueryVo.getChildren(),prpDTask.getTaskCode())){
                    taskTreeQueryVo.setChecked(false);
                    taskTreeQueryVo.setExpand(true);
                }
                flag = true;
            }
        }
        return flag;
    }

    public Integer saveTaskRoleRelated(TaskTreeSaveVo taskTreeSaveVo) {
        PrpDRoleTask condition = new PrpDRoleTask();
        condition.setRoleId(taskTreeSaveVo.getRoleCode());
        prpDRoleTaskDao.deleteByCondition(condition);

        List<PrpDRoleTask> saveList = new ArrayList<>();
        taskTreeSaveVo.getTaskIds().forEach(item -> {
            PrpDRoleTask prpDRoleTask = new PrpDRoleTask();
            prpDRoleTask.setTaskId(item);
            saveList.add(prpDRoleTask);
        });
        saveList.forEach(item -> {item.setRoleId(taskTreeSaveVo.getRoleCode());});

        return prpDRoleTaskDao.insertBatch(saveList);
    }

}
