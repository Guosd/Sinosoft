package com.sinosoft.nimg.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Guosd on 2020/02/03.
 */
@Slf4j
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class UpdateTimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("updateTimeInterceptor execute...");
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 获取SQL命令类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数对象
        Object parameter = invocation.getArgs()[1];
        if (parameter != null) {
            //解决批量插入数据时注解失效问题
            if (parameter instanceof Map){
               Map parameterMap = (Map)parameter;
               if (!SqlCommandType.DELETE.equals(sqlCommandType)) {
                   Object parameterObject = parameterMap.get("collection");
                   if (parameterObject instanceof List) {
                       List parameterList = (List) parameterObject;
                       for (Object parameters : parameterList) {
                           addCurrentTime(sqlCommandType, parameters);
                       }
                   }
               }
            }else {
                addCurrentTime(sqlCommandType, parameter);
            }
        }

        return invocation.proceed();
    }

    /**
     * 添加当前时间
     * @param sqlCommandType
     * @param parameter
     * @throws IllegalAccessException
     */
    private void addCurrentTime(SqlCommandType sqlCommandType, Object parameter) throws IllegalAccessException {
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (StringUtils.equals("updateTime", field.getName())) {
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    processField(field, parameter,1);
                }
            } else if (StringUtils.equals("createTime", field.getName())) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    processField(field, parameter,1);
                }
            } else if(StringUtils.equals("id", field.getName())){
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    processField(field, parameter,2);
                }
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 为变量赋值
     * @param field
     * @param object
     * @param type 1: 日期类型，2：UUID
     * @throws IllegalAccessException
     */
    private void processField(Field field, Object object, int type) throws IllegalAccessException {
        field.setAccessible(true);
        switch (type){
            case 1:
                field.set(object, new Date());
                break;
            case 2:
                field.set(object, UUID.randomUUID().toString().replace("-", ""));
                break;
        }

        log.debug("field '{}' is automatically updated", field.getName());
    }


}
