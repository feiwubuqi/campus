package com.campus.domain;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class TokenUtils {
    /**
     * token过期时间
     */
    private static final long EXPIRE_TIME=15*60*1000;
    private static final String TOKEN_SECRET="8f2e2d4bb0a34cfe9b2370b3d1ef4b59";

    public static String sign(String username,String userId){
try {
    Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
    Map<String,Object> heder=new HashMap<>();
    heder.put("typ","JWT");
    heder.put("alg","HS256");
    return   JWT.create()
            .withHeader(heder)
            .withClaim("loginName",username)
            .withClaim("userId",userId)
            .withExpiresAt(date)
            .sign(algorithm);
}catch(IOException e){
System.out.println("token exception" +e);
        }
return null;
    }
    public static boolean verity(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
