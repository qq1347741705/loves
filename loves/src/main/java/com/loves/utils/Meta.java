package com.loves.utils;

import lombok.Data;

/**
 * 状态码 信息
 */
@Data
public class Meta {
    private String msg;
    private Integer status;

    public Meta(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public Meta() {

    }
}
