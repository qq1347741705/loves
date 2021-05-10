package com.loves.utils;

import lombok.Data;
@Data
/**
 * 返回给前端的数据
 * data中存数据
 * meta中存信息（错误或成功及原因）
 */
public class Result {
    private Object data;
    private Meta meta;
    public Result(){
        this.meta = new Meta();
    }
}
