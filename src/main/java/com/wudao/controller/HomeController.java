package com.wudao.controller;

import com.wudao.entity.Log;
import com.wudao.result.Result;
import com.wudao.service.LogService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/web")
public class HomeController {
    @Resource
    private LogService logService;

    @RequestMapping(value = "unauthorized")
    @ResponseBody
    public Result unauthorized(HttpServletRequest request) throws Exception {
        Result result = new Result();
        result.setCode(403);
        result.setMessage("没有通过认证,请重新登陆");
        return result;
    }


    @GetMapping("/logout")
    public String logout()throws Exception{
        logService.save(new Log(Log.LOGOUT_ACTION,"用户注销"));
        SecurityUtils.getSubject().logout();
        return "redirect:/login.html";
    }
}
