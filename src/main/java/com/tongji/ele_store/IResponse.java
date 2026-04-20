package com.tongji.ele_store;

import lombok.Data;

@Data
public class IResponse<T> {
    private T data;
    private boolean success;
    private Integer code;
    private String msg;

    public static <T> IResponse<T> ok() {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(true);
        iResponse.setData(null);
        iResponse.setCode(200);
        return iResponse;
    }

    public static <T> IResponse<T> ok(T data) {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(true);
        iResponse.setData(data);
        iResponse.setCode(200);
        return iResponse;
    }

    public static <T> IResponse<T> ok(String msg) {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(true);
        iResponse.setCode(200);
        return iResponse;
    }

    public static <T> IResponse<T> ok(T data, String msg) {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(true);
        iResponse.setMsg(msg);
        iResponse.setData(data);
        iResponse.setCode(200);
        return iResponse;
    }
    public static <T> IResponse<T> error(T data) {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(false);
        iResponse.setData(data);
        iResponse.setCode(500);
        return iResponse;
    }

    public static <T> IResponse<T> error(T data,String msg) {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(false);
        iResponse.setData(data);
        iResponse.setMsg(msg);
        iResponse.setCode(500);
        return iResponse;
    }

    public static <T> IResponse<T> error(T data,String msg, Integer code) {
        IResponse<T> iResponse = new IResponse<>();
        iResponse.setSuccess(false);
        iResponse.setData(data);
        iResponse.setMsg(msg);
        iResponse.setCode(code);
        return iResponse;
    }

    public static IResponse<String> error(String msg) {
        IResponse<String> iResponse = new IResponse<>();
        iResponse.setSuccess(false);
        iResponse.setMsg(msg);
        iResponse.setCode(500);
        return iResponse;
    }
}