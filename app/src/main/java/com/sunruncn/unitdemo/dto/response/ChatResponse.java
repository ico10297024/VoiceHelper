package com.sunruncn.unitdemo.dto.response;

/**
 * Created by root on 18-7-23.
 */

public class ChatResponse {
    //错误码，为0时表示成功
    private int error_code;
    //错误信息，errno!= 0 时存在
    private String error_msg;
    //返回数据对象，当errno为0时有效
    private Chat1Response result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Chat1Response getResult() {
        return result;
    }

    public void setResult(Chat1Response result) {
        this.result = result;
    }
}
