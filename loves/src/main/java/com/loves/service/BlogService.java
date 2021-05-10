package com.loves.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loves.entity.BlogInfo;
import com.loves.mapper.BlogMapper;
import com.loves.utils.JWTUtil;
import com.loves.utils.Meta;
import com.loves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public Object getAllBlog(int pageNum, int PageSize){
        PageHelper.startPage(pageNum, PageSize);
        Page<BlogInfo> pages = blogMapper.getAllBlog();
        Result result = new Result();
        Map<String, Object> data = new HashMap<>();
        data.put("blogInfo",pages.getResult());
        data.put("total", pages.getPages());
        result.setData(data);
        result.getMeta().setStatus(200);
        result.getMeta().setMsg("获取所有文章成功");
        return result;
    }

    public Object getBlog(Integer id){
        Result result = new Result();
        result.setData(blogMapper.getBlog(id));
        result.getMeta().setMsg("获取文章成功");
        result.getMeta().setStatus(200);
        return result;
    }

    public Object deleteBlog(Integer id, HttpServletRequest request) {
        String token = request.getHeader("token");
        Result result = new Result();
        try {
            String account = JWTUtil.checkToken(token);
            int count = blogMapper.deleteBlog(id, account);
            if(count > 0){
                result.getMeta().setStatus(200);
                result.getMeta().setMsg("删除文章成功");
            }else {
                result.getMeta().setStatus(1005);
                result.getMeta().setMsg("删除文章失败");
            }
        } catch (Exception e) {
            result.getMeta().setStatus(1005);
            result.getMeta().setMsg("删除文章失败");
        }
        return result;
    }

    public Object uploadBlog (Map<String , String> map, HttpServletRequest request) {
        String token = request.getHeader("token");
        String content = map.get("content");
        String title = map.get("title");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = simpleDateFormat.format(new Date());
        Result result = new Result();
        try {
            String author = JWTUtil.checkToken(token);
            blogMapper.uploadBlog(author ,title, content, new Date().getTime(),0, time);
            result.getMeta().setMsg("文章上传成功");
            result.getMeta().setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.getMeta().setMsg("文章上传失败");
            result.getMeta().setStatus(1006);
        }
        return result;
    }
}
