package com.wudao.advice;


import com.wudao.exception.MyException;
import com.wudao.result.Result;
import com.wudao.result.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义错误类
 */
@ControllerAdvice
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e){
        logger.error(e.getLocalizedMessage());
        return Result.builder()
                .code(ResultEnum.EXCEPTION.getCode())
                .message(ResultEnum.EXCEPTION.getMsg())
                .build();
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myException(MyException e){
        logger.error(e.getLocalizedMessage());
        Integer code = e.getCode();
        String message = e.getMessage();
        if(code == null){
            code = ResultEnum.EXCEPTION.getCode();
        }
        if(message == null){
            message = ResultEnum.EXCEPTION.getMsg();
        }
        return Result.builder()
                .code(code)
                .message(message)
                .build();

    }

}
