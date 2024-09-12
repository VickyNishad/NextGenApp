package com.nextgen.login.model;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.List;

public class ResponseModel {
    private String status ;
    private String message;
    private HashMap<String, Object> response;

    public ResponseModel(){

    }

    public ResponseModel(String status, String message, HashMap<String, Object> response){
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public HashMap<String, Object> getSuccess() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setSuccess(HashMap<String, Object> response) {
        this.response = response;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

