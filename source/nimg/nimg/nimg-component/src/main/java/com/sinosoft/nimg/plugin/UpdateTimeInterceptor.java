package com.sinosoft.nimg.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
                    processDateField(field, parameter);
                }
            } else if (StringUtils.equals("createTime", field.getName())) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    processDateField(field, parameter);
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

    private void processDateField(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.get(object) == null || StringUtils.equals("updateTime", field.getName())) {
            field.set(object, new Date());
        }
        log.debug("field '{}' is automatically updated", field.getName());
    }

}
