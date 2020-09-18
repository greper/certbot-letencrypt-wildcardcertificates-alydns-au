package com.ssl.manager.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/22$
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 0=success
     */
    private int code = 0;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static<T> Result<T> error(int code, String msg, T data) {
        return new Result<>(code,msg,data);
    }
    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(1,msg,data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(0,msg,data);
    }
    public static <T> Result<T> success() {
        return new Result<>(0,"success",null);
    }
}
