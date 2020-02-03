/**
 * 系统代码表
 */
create table prp_d_code
(
    id          varchar2(32),
    code_type   varchar2(32),
    code_code   varchar2(32),
    code_c_name varchar2(32),
    code_e_name varchar2(32),
    code_t_name varchar2(32),
    display_no  number,
    delete_flag varchar2(2),
    create_by   varchar2(32),
    update_By   varchar2(32),
    create_Time Date,
    update_Time Date
);


comment on table prp_d_code is '系统转码表';
comment on column prp_d_code.id is '主键';

comment on column prp_d_code.code_type is '代码类型';
comment on column prp_d_code.code_code is '代码';

comment on column prp_d_code.code_c_name is '中文名称';
comment on column prp_d_code.code_e_name is '英文名称';
comment on column prp_d_code.code_t_name is '繁体名称';

comment on column prp_d_code.display_no is '显示序号';
comment on column prp_d_code.delete_flag is '删除标志';

comment on column prp_d_code.create_by is '创建者';
comment on column prp_d_code.create_Time is '创建时间';
comment on column prp_d_code.update_By is '更新者';
comment on column prp_d_code.update_Time is '更新时间';

/**
 * 系统用户表
 */
create table prp_d_user
(
    id          varchar2(32),
    user_code   varchar2(32),
    user_c_name varchar2(32),
    com_code    varchar2(32),

    password    varchar2(32),
    key         varchar2(32),
    iv          varchar2(32),
    delete_flag varchar2(2),
    system      varchar2(32),
    create_by   varchar2(32),
    update_By   varchar2(32),
    create_Time Date,
    update_Time Date
);


comment on table prp_d_user is '用户信息表';
comment on column prp_d_user.id is '主键';
comment on column prp_d_user.user_code is '用户代码';
comment on column prp_d_user.user_c_name is '用户名称';
comment on column prp_d_user.com_code is '机构代码';
comment on column prp_d_user.password is '密码';
comment on column prp_d_user.key is 'aes加密key';
comment on column prp_d_user.iv is 'aes加密偏移量';
comment on column prp_d_user.delete_flag is '删除标志';
comment on column prp_d_user.system is '系统代码';

comment on column prp_d_user.create_by is '创建者';
comment on column prp_d_user.create_Time is '创建时间';
comment on column prp_d_user.update_By is '更新者';
comment on column prp_d_user.update_Time is '更新时间';

/**
 * 用户任务表
 */
create table prp_d_task
(
    id          varchar2(32),
    task_code   varchar2(128),
    parent_code varchar2(128),
    task_name   varchar2(128),
    url         varchar2(128),
    system      varchar2(32),
    delete_flag varchar2(2),
    create_by   varchar2(32),
    update_By   varchar2(32),
    create_Time Date,
    update_Time Date
);

comment on table prp_d_task is '用户任务表';
comment on column prp_d_task.id is '主键';

comment on column prp_d_task.task_code is '任务代码';
comment on column prp_d_task.parent_code is '父任务代码';
comment on column prp_d_task.task_name is '任务名称';
comment on column prp_d_task.url is '任务链接';
comment on column prp_d_task.system is '系统代码';
comment on column prp_d_task.delete_flag is '删除标志';

comment on column prp_d_task.create_by is '创建者';
comment on column prp_d_task.create_Time is '创建时间';
comment on column prp_d_task.update_By is '更新者';
comment on column prp_d_task.update_Time is '更新时间';


/**
 * 用户角色表
 */
create table prp_d_role
(
    id          varchar2(32),
    role_code   varchar2(128),
    role_name   varchar2(128),
    system      varchar2(32),
    delete_flag varchar2(2),
    create_by   varchar2(32),
    update_By   varchar2(32),
    create_Time Date,
    update_Time Date
);

comment on table prp_d_role is '用户角色表';

comment on column prp_d_role.id is '主键';

comment on column prp_d_role.role_code is '角色代码';
comment on column prp_d_role.role_name is '角色名称';
comment on column prp_d_role.system is '系统代码';
comment on column prp_d_role.delete_flag is '删除标志';

comment on column prp_d_role.create_by is '创建者';
comment on column prp_d_role.create_Time is '创建时间';
comment on column prp_d_role.update_By is '更新者';
comment on column prp_d_role.update_Time is '更新时间';

/* 用户角色关联表 */
create table prp_d_user_role
(
    id          varchar2(32),
    user_id     varchar2(32),
    role_id     varchar2(32),

    delete_flag varchar2(2),
    create_by   varchar2(32),
    update_By   varchar2(32),
    create_Time Date,
    update_Time Date
);

comment on table prp_d_user_role is '用户角色关联表';
comment on column prp_d_user_role.id is '主键';

comment on column prp_d_user_role.user_id is '用户主键';
comment on column prp_d_user_role.role_id is '角色主键';

comment on column prp_d_user_role.delete_flag is '删除标志';
comment on column prp_d_user_role.create_by is '创建者';
comment on column prp_d_user_role.create_Time is '创建时间';
comment on column prp_d_user_role.update_By is '更新者';
comment on column prp_d_user_role.update_Time is '更新时间';

/* 角色任务关联表 */
create table prp_d_role_task
(
    id          varchar2(32),
    task_id     varchar2(32),
    role_id     varchar2(32),

    delete_flag varchar2(2),
    create_by   varchar2(32),
    update_By   varchar2(32),
    create_Time Date,
    update_Time Date
);

comment on table prp_d_role_task is '角色任务关联表';

comment on column prp_d_role_task.id is '主键';

comment on column prp_d_role_task.task_id is '任务主键';
comment on column prp_d_role_task.role_id is '角色主键';

comment on column prp_d_role_task.delete_flag is '删除标志';
comment on column prp_d_role_task.create_by is '创建者';
comment on column prp_d_role_task.create_Time is '创建时间';
comment on column prp_d_role_task.update_By is '更新者';
comment on column prp_d_role_task.update_Time is '更新时间';


alter table prp_d_code
    add constraint pk_prp_d_code primary key (id);

alter table prp_d_user
    add constraint pk_prp_d_user primary key (id);

alter table prp_d_task
    add constraint pk_prp_d_task primary key (id);

alter table prp_d_role
    add constraint pk_prp_d_role primary key (id);

alter table prp_d_user_role
    add constraint pk_prp_d_user_role primary key (id);

alter table prp_d_user_role
    add constraint fk_userrole_userid foreign key (user_id) references prp_d_user(id);

alter table prp_d_user_role
    add constraint fk_userrole_roleid foreign key (role_id) references prp_d_role(id);

alter table prp_d_role_task
    add constraint pk_prp_d_role_task primary key (id);

alter table prp_d_role_task
    add constraint fk_roletask_roleid foreign key (task_id) references prp_d_task(id);

alter table prp_d_role_task
    add constraint fk_roletask_taskid foreign key (role_id) references prp_d_role(id);


