package com.wudao.realm;

import com.wudao.entity.JwtToken;
import com.wudao.entity.User;
import com.wudao.service.UserService;
import com.wudao.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String credentials = (String)token.getCredentials();
        String username = JwtUtil.getUsername(credentials);
        if(username == null){
            throw new AuthenticationException("token invalid");
        }

        User user = userService.findByUserName(username);
        if(user == null){
            throw new AuthenticationException("user not exist!");
        }

        if(!JwtUtil.verify(credentials, username, user.getPassword())){
            throw new AuthenticationException("Username or password invalid");
        }

        return new SimpleAuthenticationInfo(credentials,credentials,"my_realm");
    }
}
