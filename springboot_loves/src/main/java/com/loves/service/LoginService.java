package com.loves.service;

import com.loves.entity.UserInfo;
import com.loves.mapper.LoginMapper;
import com.loves.utils.JWTUtil;
import com.loves.utils.Meta;
import com.loves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public Result login(String username, String password){
        Result result = new Result();
        UserInfo user;
        user = loginMapper.getInfoByName(username);
        if(user == null){
            Meta meta = new Meta("账号不存在",1003);
            result.setMeta(meta);
        }else {
            if(password.equals(user.getPassword())){
                Meta meta = new Meta("密码正确",200);
                result.setMeta(meta);
                Map<String,Object> data = new HashMap<>();
                data.put("token",JWTUtil.getToken(username));
                data.put("currentUser",user.getAccount());
                data.put("username",user.getName());
                result.setData(data);
            }else {
                Meta meta = new Meta("密码错误",1004);
                result.setMeta(meta);
            }
        }
        return result;
    }

    public Object getImg(HttpServletRequest request){
        String token = request.getHeader("token");
        String account;
        Result result = new Result();
        try {
            account = JWTUtil.checkToken(token);
            String imgPath = loginMapper.getImg(account);
            Map<String, String> data = new HashMap<>();
            data.put("img",imgPath);
            result.setData(data);
            Meta meta = new Meta();
            meta.setStatus(200);
            meta.setMsg("获取用户头像成功");
            result.setMeta(meta);
        } catch (Exception e) {
            Meta meta = new Meta();
            meta.setMsg("获取用户头像失败");
            meta.setStatus(1002);
            result.setMeta(meta);
        }
        return result;
    }
}
