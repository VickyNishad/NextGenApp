package com.nextgen.sms.service;

import com.nextgen.sms.models.RequestModel;
import com.nextgen.sms.models.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface EmailSMSService {
    public ResponseEntity<?> validateRequest(RequestModel requestModel);
    public ResponseEntity<?> prepareRequest(RequestModel requestModel);
    public ResponseEntity<?> executeRequest(RequestModel requestModel,Object object);
    public ResponseEntity<?> prepareHttpRequest(RequestModel requestModel);
    public ResponseEntity<?> executeHttpRequest(RequestModel requestModel);
    public ResponseEntity<?> finalResponse(RequestModel requestModel, ResponseModel responseModel);
    public ResponseEntity<?> processRequest(EmailSMSService emailSMSService, RequestModel requestModel);

}
