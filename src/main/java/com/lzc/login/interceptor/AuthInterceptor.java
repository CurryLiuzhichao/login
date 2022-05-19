package com.lzc.login.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lzc.login.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            JSONObject result  = new JSONObject();
            result.put("returnCode",10001);
            result.put("returnMsg","用户未登录，请登录后操作！");
            return false;
        }
        Object loginStatus = redisService.get(token);
        if( Objects.isNull(loginStatus)){
            JSONObject result  = new JSONObject();
            result.put("returnCode",10002);
            result.put("returnMsg","此session已经过期，请重新登录");
            response.getWriter().print(result);
            return false;
        }
        redisService.update(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
