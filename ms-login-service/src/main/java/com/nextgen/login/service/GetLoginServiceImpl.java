package com.nextgen.login.service;

import com.nextgen.login.model.RequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetLoginServiceImpl implements LoginService{
    @Override
    public ResponseEntity<?> validateRequest(RequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> prepareRequest(RequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> executeRequest(RequestModel requestModel,Object object) {
        return null;
    }

    @Override
    public ResponseEntity<?> prepareHttpRequest(RequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> executeHttpRequest(RequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> finalResponse(RequestModel requestModel,Object object) {
        return null;
    }

    @Override
    public ResponseEntity<?> processRequest(LoginService loginService, RequestModel requestModel) {
        return null;
    }
}
