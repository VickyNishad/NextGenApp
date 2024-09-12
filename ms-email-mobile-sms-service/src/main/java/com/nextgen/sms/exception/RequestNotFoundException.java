package com.nextgen.sms.exception;

public class RequestNotFoundException extends RuntimeException{

    private String status;
    private String errorMessage;

    public RequestNotFoundException(String status , String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
