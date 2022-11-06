package com.orange.verify.api.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -7664978553569815329L;

    private int code;

    private String msg;

    private T data;

    private Response() {}

    private Response(RspCode rspCode) {
        this.code = rspCode.getCode();
        this.msg = rspCode.getMessage();
    }

    private Response(RspCode rspCode, T data) {
        this.code = rspCode.getCode();
        this.msg = rspCode.getMessage();
        this.data = data;
    }

    private Response(RspCode rspCode, String msg) {
        this.code = rspCode.getCode();
        this.msg = msg;
    }

    private Response(RspCode rspCode, String msg, T data) {
        this.code = rspCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static Response success() {
        return new Response(RspCode.SUCCESS);
    }

    public static<T> Response<T> success(T data) {
        return new Response(RspCode.SUCCESS, RspCode.SUCCESS.getMessage(), data);
    }

    public static Response error() {
        return new Response(RspCode.ERROR);
    }

    public static Response build(RspCode rspCode) {
        return new Response(rspCode);
    }

    public static<T> Response<T> build(RspCode rspCode, T data) {
        return new Response(rspCode, data);
    }

    public static Response build(RspCode rspCode, String msg) {
        return new Response(rspCode, msg);
    }

    public static<T> Response<T> build(RspCode rspCode, String msg, T data) {
        return new Response(rspCode, msg, data);
    }

}
