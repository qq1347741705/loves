package com.loves.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JWTUtil {

    private static final Long EXPIRATION_TIME = 60*60*1000 * 2L; //有效期默认两个小时
    private static final String SECRET = "qianrentao";   //秘钥
    public static String getToken(String name){
        String token = "";
        long time = new Date().getTime() + EXPIRATION_TIME;
        try {
            token = JWT.create()
                    .withExpiresAt(new Date(time))
                    .withClaim("account",name)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String checkToken(String token) throws Exception{
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        return verify.getClaim("account").asString();
    }
}
