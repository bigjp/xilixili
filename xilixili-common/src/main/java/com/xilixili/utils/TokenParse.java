package com.xilixili.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenParse {
    public static String getUsernameByToken(String token,String tokenSalt){
        if(token==null){
            return null;
        }
        Claims body = Jwts.parser().setSigningKey(tokenSalt).parseClaimsJws(token).getBody();
        return body.getSubject();
    }

    public static String getUserRoleByToken(String token,String tokenSalt,String role){
        if(token==null){
            return null;
        }
        Claims body = Jwts.parser().setSigningKey(tokenSalt).parseClaimsJws(token).getBody();
        return (String) body.get(role);
    }
}
