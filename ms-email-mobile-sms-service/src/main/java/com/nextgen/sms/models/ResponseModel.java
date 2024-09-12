package com.nextgen.sms.models;

import java.util.HashMap;

public class ResponseModel {
    private String status ;
    private String message;
    private Object responseData;

    public ResponseModel(){

    }

    public ResponseModel(String status, String message, Object responseData){
        this.status = status;
        this.message = message;
        this.responseData = responseData;
    }

    public Object getResponseData() {
        return responseData;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

