package com.wudao.result;


/**
 * 处理请求失败与成功的常用工具方法
 */

public class ResultUtils {
    public static <T> Result success(T data){
        return Result.builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static  Result success(Integer code, String msg){
        if (null==msg) msg = ResultEnum.SUCCESS.getMsg();
        if (null==code) code = ResultEnum.SUCCESS.getCode();
        return Result.builder()
                .code(code)
                .message(msg)
                .build();
    }

    public static Result success(ResultEnum resultEnum){
        return success(resultEnum.getCode(),resultEnum.getMsg());
    }

    public static <T> Result fail(T data){
        return Result.builder()
                .code(ResultEnum.FAIL.getCode())
                .message(ResultEnum.FAIL.getMsg())
                .data(data)
                .build();
    }

    public static Result fail(Integer code, String msg){
        if (null==msg) msg = ResultEnum.FAIL.getMsg();
        if (null==code) code = ResultEnum.FAIL.getCode();
        return Result.builder()
                .code(code)
                .message(msg)
                .build();
    }

    public static Result fail(ResultEnum resultEnum){
        return fail(resultEnum.getCode(),resultEnum.getMsg());
    }






}
