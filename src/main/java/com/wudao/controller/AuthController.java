package com.wudao.controller;

import com.wudao.entity.LoginParam;
import com.wudao.entity.User;
import com.wudao.result.Result;
import com.wudao.result.ResultUtils;
import com.wudao.service.UserService;
import com.wudao.util.JwtUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 认证模块
 */
@RestController
@RequestMapping("auth")
public class AuthController {
    private static final Logger logger= LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                        @RequestBody LoginParam loginParam){
        logger.info("用户请求登陆");
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        User user = userService.findByUserName(username);
        String salt = user.getSalt();
        int hashIterations = 512;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username+salt);
        String encodedPassword = new SimpleHash(Sha512Hash.ALGORITHM_NAME, password, credentialsSalt, hashIterations).toString();
        if(user.getPassword().equals(encodedPassword)){
           return ResultUtils.success(JwtUtil.sign(username,encodedPassword));
        }else{
            throw new UnauthenticatedException();
        }
    }

}
