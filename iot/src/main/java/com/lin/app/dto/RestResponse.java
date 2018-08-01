package com.lin.app.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lin.app.constant.ErrorCode;

public class RestResponse<T> {

    public static final String SUCCESS_CODE = "0";

    public static <T> RestResponse<T> success(T result){
        RestResponse<T> res = new RestResponse<>();
        res.setResult(result);
        res.setErrorCode(SUCCESS_CODE);
        return res;
    }

    public static <T> RestResponse<T> failure(ErrorCode code, String errorMessage){
        RestResponse<T> res = new RestResponse<>();
        res.setErrorCode(String.valueOf(code.getCode()));
        res.setErrorMessage(errorMessage==null?code.getMessage():errorMessage);
        return res;
    }

    public static <T> RestResponse<T> failure(ErrorCode code){
        return failure(code, null);
    }

    @JsonProperty("error_code")
    @JSONField(name = "error_code")
    private String errorCode;

    @JsonProperty("error_msg")
    @JSONField(name = "error_msg")
    private String errorMessage;

    @JsonProperty("result")
    @JSONField(name = "result")
    private T result;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }


}
