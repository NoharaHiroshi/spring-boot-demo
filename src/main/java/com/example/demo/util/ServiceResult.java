package com.example.demo.util;

import java.io.Serializable;

// 封装Service返回对象
// ServiceResult泛型类型，可以理解为类型作为形参的类，传入的实参是什么类，泛类最终就是什么类
public class ServiceResult<T> implements Serializable {
    private boolean success = false;

    private String code;

    private String msg;

    private T data;

    private ServiceResult() {
    }

    // 返回值存在泛型，需要在返回类型前指定<T>来表示泛型类型
    public static <T> ServiceResult<T> success(T data) {
        ServiceResult<T> item = new ServiceResult<T>();
        item.success = true;
        item.data = data;
        item.code = "0";
        item.msg = "success";
        return item;
    }

    public static <T> ServiceResult<T> failure(String errorCode, String errorMessage) {
        ServiceResult<T> item = new ServiceResult<T>();
        item.success = false;
        item.code = errorCode;
        item.msg = errorMessage;
        return item;
    }
}
