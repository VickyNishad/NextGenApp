package com.nextgen.sms.controller;

import com.nextgen.sms.models.RequestModel;
import com.nextgen.sms.service.EmailSMSService;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected  ResponseEntity<?> processRequest(EmailSMSService emailSMSService, RequestModel requestModel){
        return emailSMSService.processRequest(emailSMSService,requestModel);
    }
    public abstract ResponseEntity<?> submitDetails( RequestModel requestModel);
    public abstract ResponseEntity<?> getDetails( RequestModel requestModel);
    public abstract ResponseEntity<?> updateDetails( RequestModel requestModel);

}