package com.wudao.advice;


import com.wudao.exception.MyException;
import com.wudao.result.Result;
import com.wudao.result.ResultEnum;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义错误类
 */
@ControllerAdvice
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);


    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return new Result(401, "shiro的异常", null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401() {
        return new Result(401, "UnauthorizedException", null);
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


    // 捕捉其他所有异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e){
        logger.error(e.getLocalizedMessage());
        return Result.builder()
                .code(ResultEnum.EXCEPTION.getCode())
                .message(ResultEnum.EXCEPTION.getMsg())
                .build();
    }



}
