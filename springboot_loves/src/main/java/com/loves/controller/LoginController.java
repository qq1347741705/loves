package com.loves.controller;

import com.loves.entity.UserInfo;
import com.loves.service.LoginService;
import com.loves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@ResponseBody
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody UserInfo userInfo){
        return loginService.login(userInfo.getAccount(),userInfo.getPassword());
    }

    @GetMapping("/getImg")
    public Object getImg(HttpServletRequest request){
        return loginService.getImg(request);
    }
}
