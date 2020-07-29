package com.qf.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class SubZuulFilter extends ZuulFilter {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        if(requestURL!=null||requestURL.equals("/eureka-userAuthorization/login")
            ||requestURL.equals("/eureka-userAuthorization/regis") ){
        return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Map<String,Object> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        String tokenByCookies = getTokenByCookies(cookies);
        if(tokenByCookies==null || tokenByCookies.length()==0){
            map.put("msg","token is null");
            Object o = JSONObject.toJSON(map);
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody(o.toString());
        }else {
            if(!redisTemplate.hasKey("token")){
                map.put("msg","token is expried");
                Object o = JSONObject.toJSON(map);
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseBody(map.toString());
            }
            String str = (String) redisTemplate.opsForValue().get("token");
            if(!tokenByCookies.equals(str)){
                map.put("msg","token is error");
                Object o = JSONObject.toJSON(map);
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseBody(map.toString());
            }
        }
        return null;
    }


    public String getTokenByCookies(Cookie[] cookies){
        for (Cookie c:cookies
             ) {
            if("token".equals(c.getName())){
                return c.getValue();
            }
        }
        return null;
    }
}
