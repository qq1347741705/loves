package com.loves.entity;

import lombok.Data;

@Data
public class BlogInfo {
    private Integer id;
    private String author;
    private String title;
    private String content;
    private Long createTime;
    private Integer likes;
    private String strTime;
    private String name;
}
