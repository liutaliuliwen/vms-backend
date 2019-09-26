package com.wudao.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyException extends RuntimeException {
    //错误码
    private Integer code;

    public MyException(String msg){
        super(msg);
    }

    public MyException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
}
