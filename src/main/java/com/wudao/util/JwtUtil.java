package com.wudao.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wudao.advice.ExceptionAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    //5 分钟过期
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     *
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */

    public static String sign(String username, String secret){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //附带username信息
        return JWT.create()
                .withClaim("username",username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token, String username, String secret) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (Exception exception){
            return false;
        }
    }


    /**
     *
     * @param token
     * @return 获取token中的用户名
     */

    public static String getUsername(String token) {
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }


}
