
alter table prp_d_role_task
    drop constraint fk_roletask_roleid;

alter table prp_d_role_task
    drop constraint fk_roletask_taskid;

alter table prp_d_user_role
    drop constraint fk_userrole_userid;

alter table prp_d_user_role
    drop constraint fk_userrole_roleid;



alter table prp_d_code
    drop constraint pk_prp_d_code;

alter table prp_d_user
    drop constraint pk_prp_d_user;

alter table prp_d_task
    drop constraint pk_prp_d_task;

alter table prp_d_role
    drop constraint pk_prp_d_role;

alter table prp_d_user_role
    drop constraint pk_prp_d_user_role;

alter table prp_d_role_task
    drop constraint pk_prp_d_role_task;


drop table prp_d_code;
drop table prp_d_user;
drop table prp_d_task;
drop table prp_d_role;
drop table prp_d_user_role;
drop table prp_d_role_task;

