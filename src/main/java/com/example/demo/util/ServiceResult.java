package com.example.demo.util;

import javax.xml.crypto.Data;
import java.io.Serializable;

// 封装Service返回对象
// ServiceResult泛型类型，可以理解为类型作为形参的类，传入的实参是什么类，泛类最终就是什么类
public class ServiceResult<T> implements Serializable {
    private boolean success = false;

    private String code;

    private String msg;

    private T data;

    public ServiceResult() {
    }

    // 返回值存在泛型，需要在返回类型前指定<T>来表示泛型类型
    public ServiceResult<T> success(T data) {
        this.success = true;
        this.data = data;
        this.code = "0";
        this.msg = "success";
        return this;
    }

    public ServiceResult<T> failure(String errorCode, String errorMessage) {
        this.success = false;
        this.code = errorCode;
        this.msg = errorMessage;
        return this;
    }

    public boolean hasData() {
        return data != null;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
