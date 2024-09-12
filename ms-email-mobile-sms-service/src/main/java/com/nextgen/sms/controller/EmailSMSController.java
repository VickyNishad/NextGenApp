package com.nextgen.sms.controller;

import com.nextgen.sms.models.RequestModel;
import com.nextgen.sms.service.SubmitEmailSMSDetailsServiceImpl;
import com.nextgen.sms.service.UpdateEmailSMSDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/otp")
@RestController
public class EmailSMSController extends  BaseController{
    @Autowired
    private SubmitEmailSMSDetailsServiceImpl submitOtpDetailsService;
    @Autowired
    private UpdateEmailSMSDetailsServiceImpl updateOtpDetailsService;
    @PostMapping("/submit_details")
    @Override
    public ResponseEntity<?> submitDetails(@RequestBody RequestModel requestModel) {
        return this.processRequest(submitOtpDetailsService,requestModel);
    }

    @Override
    public ResponseEntity<?> getDetails(RequestModel requestModel) {
        return null;
    }

    @PostMapping("/update_details")
    @Override
    public ResponseEntity<?> updateDetails(@RequestBody RequestModel requestModel) {
        return this.processRequest(updateOtpDetailsService,requestModel);
    }
}
