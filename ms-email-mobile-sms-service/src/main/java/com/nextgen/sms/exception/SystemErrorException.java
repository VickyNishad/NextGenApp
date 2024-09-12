package com.nextgen.sms.exception;

public class SystemErrorException extends  RuntimeException{
    private String status;
    private String errorMessage;

    public SystemErrorException(String status , String errorMessage){
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
