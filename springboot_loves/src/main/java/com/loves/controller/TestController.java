package com.loves.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class TestController {

    @GetMapping("/test")
    public void test(HttpServletResponse response){
        Map<String , String> result = new HashMap<>();
        result.put("status","1000");
        result.put("msg","登陆信息过期");
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test2")
    public String test2(String name){
        return name;
    }

    @GetMapping("/test1")
    public String test1(){
        return "hello1";
    }
}
