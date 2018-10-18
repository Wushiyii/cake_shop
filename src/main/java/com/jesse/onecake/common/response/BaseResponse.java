package com.jesse.onecake.common.response;

import org.springframework.http.HttpStatus;

public class BaseResponse<T> {
    //状态码
    private int code;

    //消息
    private String msg;

    //内容
    private Object data;

    public BaseResponse(int status, String msg) {
        this.code = HttpStatus.OK.value();
        this.msg = msg;
    }

    public BaseResponse() {
        this.code = HttpStatus.OK.value();;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int status) {
        this.code = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResponse succ() {
        return new BaseResponse(HttpStatus.OK.value(),"success");
    }

    public static BaseResponse error(String msg) {
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
    }

    public static BaseResponse error() {
        return error("error");
    }

    public boolean isSucc(){
        return this.code == 200;
    }
    public BaseResponse withData(Object data) {
        this.setData(data);
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
