package com.loves.config;

import com.alibaba.fastjson.JSON;
import com.loves.utils.JWTUtil;
import com.loves.utils.Meta;
import com.loves.utils.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null){
            Result result = new Result();
            Meta meta = new Meta();
            meta.setMsg("未登入");
            meta.setStatus(1000);
            result.setMeta(meta);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        } else{
            try {
                JWTUtil.checkToken(token);
            }catch (Exception e){
                Result result = new Result();
                Meta meta = new Meta();
                meta.setMsg("登入信息过期");
                meta.setStatus(1001);
                result.setMeta(meta);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(result));
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
