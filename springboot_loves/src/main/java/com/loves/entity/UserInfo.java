package com.loves.entity;

import lombok.Data;

/**
 * 接收用户名用户密码
 */
@Data
public class UserInfo {
    private Long id;
    private String account;
    private String password;
    private String img;
    private String name;
}
