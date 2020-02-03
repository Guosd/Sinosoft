package com.sinosoft.nimg.advice;

import com.github.framework.evo.common.exception.BusinessException;
import com.github.framework.evo.common.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sino on 2019/9/7.
 */
@Slf4j
@ControllerAdvice
@Order(1)
public class ApiAdvice {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @ExceptionHandler({BusinessException.class, IllegalArgumentException.class,
            BindException.class, MethodArgumentNotValidException.class,
            IOException.class, RuntimeException.class, StackOverflowError.class,
            ReflectiveOperationException.class,Exception.class})
    @ResponseBody
    public ResponseVo<Object> customErrorHandler(Exception ex) throws Exception {
        Annotation an = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if(an != null) {
            throw ex;
        } else {
            ResponseVo<Object> resp = new ResponseVo(ex);
            log.warn("{}", ex.getMessage(), ex);
            return resp;
        }
    }

}
