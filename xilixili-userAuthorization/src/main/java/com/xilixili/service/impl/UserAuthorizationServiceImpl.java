package com.xilixili.service.impl;

import com.xilixili.client.UserClient;
import com.xilixili.service.UserAuthorizationService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service("userAuthorizationService")
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

    @Autowired
    UserClient userClient;
    @Autowired
    RedisTemplate redisTemplate;
    @Value("${tokenSalt}")
    String tokenSalt;
    @Value("${redisTokenExpires}")
    int redisTokenExpires;
    @Value("${cookieTokenExpires}")
    int cookieTokenExpires;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据用户名和密码判断用户是否登录成功，登录成功后生成携带用户信息的token
     * @param user 登录用户名和密码
     * @return  返回登录结果
     */
    @Override
    public String login(User user) {
        if(user==null){
            return "user is null";
        }
        User userByUsername = userClient.findUserByUsername(user);
        if(userByUsername==null){
            return "username is null";
        }
        boolean matches = bCryptPasswordEncoder.matches(user.getPassword(), userByUsername.getPassword());
        if(!matches){
            return "password error";
        }
        try{
            createToken(user);
            return "login success";
        }catch(Exception e){
            return "login failed";
        }
    }

    /**
     * 将用户信息存入到数据库中
     * @param user
     * @return
     */
    @Override
    public String regis(User user) {
        if(user.getEmail() == null || user.getPassword() == null){
            return "账号密码不能为空";
        }
        User userByUsername = userClient.findUserByUsername(user);
        if(userByUsername != null){
            return "用户名已存在";
        }
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        boolean b = userClient.addUser(user);
        if(b){
            return "注册成功";
        }
        return "注册失败";
    }


    /**
     * 根据用户信息生成加密后的token，存入cookie、redis中
     * @param user  用户的详细信息
     */
    void createToken(User user){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(String.valueOf(user.getUid()))
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, tokenSalt);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie token = new Cookie("token", jwtBuilder.compact());
        response.addCookie(token);
        redisTemplate.opsForValue().set("token",jwtBuilder.compact());
        redisTemplate.expire("token",redisTokenExpires, TimeUnit.SECONDS);
    }
}
