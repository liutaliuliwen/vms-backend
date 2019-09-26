package com.wudao.controller;


import com.wudao.exception.MyException;
import com.wudao.result.Result;
import com.wudao.result.ResultEnum;
import com.wudao.result.ResultUtils;
import com.wudao.util.StringUtil;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class ExceptionController {
    @RequestMapping("/exception")
    public Result exception(String name, String password) throws Exception {
        String realName = "hello";
        String realPassword = "123456";

        if(null != name && name.equals("xx")){
            throw new Exception("系统异常!");
        }
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(password)){
            throw new MyException("参数必须传! ");
        }else if(!name.equals(realName) || !password.equals(realPassword)){
            throw new MyException("用户名或者密码不正确！");
        }else if(name.equals("aa")){
            throw new MyException(200,"用户已存在!");
        }
        String info = "你好" + name+ "!";
        return ResultUtils.success(info);
    }

    @RequestMapping("/unlogin")
    public Result unlogin() {
        return ResultUtils.success(ResultEnum.UNLOGIN);
    }

    @RequestMapping("/success")
    public Result success() {
        return ResultUtils.success(200,"自定义消息");
    }

}
