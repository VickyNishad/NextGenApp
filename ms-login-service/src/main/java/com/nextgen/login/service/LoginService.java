package com.nextgen.login.service;

import com.nextgen.login.model.RequestModel;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    public ResponseEntity<?> validateRequest(RequestModel requestModel);
    public ResponseEntity<?> prepareRequest(RequestModel requestModel);
    public ResponseEntity<?> executeRequest(RequestModel requestModel,Object object);
    public ResponseEntity<?> prepareHttpRequest(RequestModel requestModel);
    public ResponseEntity<?> executeHttpRequest(RequestModel requestModel);
    public ResponseEntity<?> finalResponse(RequestModel requestModel,Object object);
    public ResponseEntity<?> processRequest(LoginService loginService , RequestModel requestModel);


}
