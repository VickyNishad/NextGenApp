package com.nextgen.login.exception;

public class UserNotFoundException extends RuntimeException{
    private String status;
    private String errorMessage;

    public UserNotFoundException(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
