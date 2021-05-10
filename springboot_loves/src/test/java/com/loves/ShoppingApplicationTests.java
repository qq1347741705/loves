package com.loves;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.loves.entity.BlogInfo;
import com.loves.mapper.BlogMapper;
import com.loves.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShoppingApplicationTests {

    @Value("${filePath}")
    private String path;

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void test(){
        
    }

    @Test
    void test2(){
        Page<BlogInfo> blogInfos = blogMapper.getAllBlog();
        System.out.println(blogInfos.toString());
    }

}
