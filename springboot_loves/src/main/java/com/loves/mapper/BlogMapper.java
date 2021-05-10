package com.loves.mapper;

import com.github.pagehelper.Page;
import com.loves.entity.BlogInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Select("select content.id,content.author,content.title,content.content," +
            "content.create_time,content.str_time,content.likes,users.name" +
            " from content left join users on content.author = users.account " +
            "order by content.create_time desc")
    Page<BlogInfo> getAllBlog();

    @Select("select * from content where id = #{id}")
    BlogInfo getBlog(Integer id);

    @Delete("delete from content where id = #{id} and author = #{account}")
    int deleteBlog(Integer id, String account);

    @Insert("insert into content (author, title,content,create_time, likes, str_time) values (#{author},#{title},#{content},#{createTime},#{likes},#{str_time})")
    void uploadBlog(String author , String title, String content ,Long createTime, Integer likes, String str_time);
}
