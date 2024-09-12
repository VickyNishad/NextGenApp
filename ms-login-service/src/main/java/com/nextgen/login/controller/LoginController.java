package com.nextgen.login.controller;

import com.nextgen.login.model.RequestModel;
import com.nextgen.login.service.GetLoginServiceImpl;
import com.nextgen.login.service.SubmitLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController extends BaseController {
    @Autowired
    private SubmitLoginServiceImpl submitLoginServiceImpl;
    @Autowired
    private GetLoginServiceImpl getLoginServiceimpl;

    @PostMapping("/submit_details")
    @Override
    public ResponseEntity<?> submitDetails(@RequestBody RequestModel requestModel) {
        return this.processRequest(submitLoginServiceImpl,requestModel);
    }

    @Override
    public ResponseEntity<?> getDetails(RequestModel requestModel) {
        return this.processRequest(getLoginServiceimpl,requestModel);
    }

    @Override
    public ResponseEntity<?> updateDetails(RequestModel requestModel) {
        return this.processRequest(getLoginServiceimpl,requestModel);
    }
}
