package com.mustar.utils;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    //密钥
    private static final String signatrue="studms";
    //创建token
    public static String createToken(String userAccount){
        JwtBuilder jwtBuilder= Jwts.builder();
        //有效时间 三天
        long time = 1000 * 60 * 60 * 24 * 3;
        return jwtBuilder
                //header
//                .setHeaderParam("typ","JWT")
//                .setHeaderParam("alg","HS256")
                //payload
                .setExpiration(new Date(System.currentTimeMillis()+ time))
//                .setHeaderParam("iss","mustar")
                .setHeaderParam("userAccount",userAccount)
                //signatrue
                .signWith(SignatureAlgorithm.HS256,signatrue)
                .compact();
    }
    //验证token
    public static String checkToken(String token){
        if(token==null){
            return "-1";
        }
        try {
            JwtParser jwtParser=Jwts.parser();
            Jws<Claims> claimsJws= jwtParser.setSigningKey(signatrue).parseClaimsJws(token);
            return claimsJws.getHeader().get("userAccount").toString();
        }catch (Exception e){
            return "-1";
        }
    }
}
