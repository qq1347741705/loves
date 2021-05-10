package com.loves.controller;

import com.loves.entity.BlogInfo;
import com.loves.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/getAllBlog")
    public Object getAllBlog(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, HttpServletRequest request){
        return blogService.getAllBlog(pageNum, pageSize);
    }

    @GetMapping("/getBlog")
    public Object getBlog(@RequestParam("id") Integer id){
        return blogService.getBlog(id);
    }

    @PostMapping("/deleteBlog")
    public Object deleteBlog(@RequestBody Map<String, Integer> map, HttpServletRequest request) {
        return blogService.deleteBlog(map.get("id"), request);
    }

    @PostMapping("/uploadBlog")  //后端想接受json 需要加@RequestBody
    public Object uploadBlog (@RequestBody Map<String , String> map, HttpServletRequest request) {
        return blogService.uploadBlog(map, request);
    }
}
