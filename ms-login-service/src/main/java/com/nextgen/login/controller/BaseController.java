package com.nextgen.login.controller;

import com.nextgen.login.model.RequestModel;
import com.nextgen.login.service.LoginService;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected  ResponseEntity<?> processRequest(LoginService loginService , RequestModel requestModel){
        try {
           return loginService.processRequest(loginService,requestModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public abstract ResponseEntity<?> submitDetails( RequestModel requestModel);
    public abstract ResponseEntity<?> getDetails( RequestModel requestModel);
    public abstract ResponseEntity<?> updateDetails( RequestModel requestModel);

}
