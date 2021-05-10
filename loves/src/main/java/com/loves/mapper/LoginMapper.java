package com.loves.mapper;

import com.loves.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from users where account = #{name}")
    UserInfo getInfoByName(String name);

    @Select("select img from users where account = #{name}")
    String getImg(String name);

}
