package com.nextgen.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextgen.login.constant.LoginConstant;
import com.nextgen.login.dto.LoginDetailsDto;
import com.nextgen.login.exception.RequestNotFoundException;
import com.nextgen.login.exception.SystemErrorException;
import com.nextgen.login.model.LoginDetails;
import com.nextgen.login.model.RequestModel;
import com.nextgen.login.model.ResponseModel;
import com.nextgen.login.repository.LoginRepository;
import com.nextgen.login.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class SubmitLoginServiceImpl implements LoginService , LoginConstant {

    private static final Logger log = LoggerFactory.getLogger(SubmitLoginServiceImpl.class);
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public ResponseEntity<?> validateRequest(RequestModel requestModel) {
        try {
            List<HashMap<String,Object>> listOfRequest = requestModel.getPayload();
            if(!listOfRequest.isEmpty()){
                for (HashMap<String ,Object> payloadRequest: listOfRequest ){
                    if(payloadRequest.containsKey(DEVICEID)){
                        if(String.valueOf(payloadRequest.get(DEVICEID)).isBlank() || String.valueOf(payloadRequest.get(DEVICEID)).isEmpty()){
                            throw new RequestNotFoundException(ERROR,"DeviceId"+ERRORMESSAGE);
                        }
                    }else{
                        throw new RequestNotFoundException(ERROR,DEVICEID+FIELDERRORMESSAGE);
                    }

                    if(payloadRequest.containsKey(LOGINTYPE)){
                        if(String.valueOf(payloadRequest.get(LOGINTYPE)).isBlank() || String.valueOf(payloadRequest.get(LOGINTYPE)).isEmpty()){
                            throw new RequestNotFoundException(ERROR,"LoginType"+ERRORMESSAGE);
                        }else{
                            if (String.valueOf(payloadRequest.get(LOGINTYPE)).equalsIgnoreCase(MOBILE)){
                                if(payloadRequest.containsKey(MOBILENUMBER)){
                                    if(String.valueOf(payloadRequest.get(MOBILENUMBER)).isBlank() ||String.valueOf(payloadRequest.get(MOBILENUMBER)).isEmpty() ){
                                        throw new RequestNotFoundException(ERROR,"MobileNumber"+ERRORMESSAGE);
                                    }
                                }else {
                                    throw new RequestNotFoundException(ERROR,MOBILENUMBER+FIELDERRORMESSAGE);
                                }
                            }

                            if (String.valueOf(payloadRequest.get(LOGINTYPE)).equalsIgnoreCase(EMAIL)){
                                if(payloadRequest.containsKey(EMAILID)){
                                    if(String.valueOf(payloadRequest.get(EMAILID)).isBlank() ||String.valueOf(payloadRequest.get(EMAILID)).isEmpty() ){
                                        throw new RequestNotFoundException(ERROR,"EmailId"+ERRORMESSAGE);
                                    }
                                }else {
                                    throw new RequestNotFoundException(ERROR,EMAILID+FIELDERRORMESSAGE);
                                }
                            }
                        }
                    }else{
                        throw new RequestNotFoundException(ERROR,LOGINTYPE+FIELDERRORMESSAGE);
                    }

                }
            }else {
                throw new RequestNotFoundException(ERROR,INVALIDREQUEST);
            }
            return  new ResponseEntity<>(requestModel , HttpStatus.OK);
        } catch (RequestNotFoundException e) {
            throw new RequestNotFoundException(e.getStatus(),e.getErrorMessage());
        } catch (Exception e){
            throw new SystemErrorException(ERROR,e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> prepareRequest(RequestModel requestModel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LoginDetailsDto loginDetailsDto = new LoginDetailsDto();
            List<HashMap<String,Object>> listOfRequest = requestModel.getPayload();
            for (HashMap<String,Object> payloadRequest : listOfRequest){
                loginDetailsDto.setMobileNumber(String.valueOf(payloadRequest.get(MOBILENUMBER)));
                loginDetailsDto.setLoginType(String.valueOf(payloadRequest.get(LOGINTYPE)));
                loginDetailsDto.setDeviceId(String.valueOf(payloadRequest.get(DEVICEID)));
                loginDetailsDto.setDeviceType(String.valueOf(payloadRequest.get(DEVICETYPE)));
                loginDetailsDto.setEmailId(String.valueOf(payloadRequest.get(EMAILID)));
            }
            return  new ResponseEntity<>(loginDetailsDto ,HttpStatus.OK);
        } catch (Exception e) {
            throw new SystemErrorException(ERROR,e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> executeRequest(RequestModel requestModel,Object object) {
        try {
            LoginDetails loginDetails = new LoginDetails();
            LoginDetailsDto loginDetailsDto = (LoginDetailsDto) object;
            List<LoginDetails> listOfLoginDetails = loginRepository.findByMobileNumber(loginDetailsDto.getMobileNumber());
            if (listOfLoginDetails==null || listOfLoginDetails.isEmpty()){
                loginDetails = new LoginDetails();
                loginDetails.setMobileNumber(loginDetailsDto.getMobileNumber());
                loginDetails.setLoginType(loginDetailsDto.getLoginType());
                loginDetails.setEmailId(loginDetailsDto.getEmailId());
                loginDetails.setDeviceType(loginDetailsDto.getDeviceType());
                loginDetails.setDeviceId(loginDetailsDto.getDeviceId());
                loginDetails.setClientCode(Utils.generateClientCode());
                loginDetails.setNewLogin(true);
                loginDetails.setActive(false);
                loginDetails.setLogin(false);
                loginDetails.setModifyOn(LocalDateTime.now());
                loginRepository.save(loginDetails);
                return new ResponseEntity<>(loginDetails , HttpStatus.OK);
            }else {
                long id = listOfLoginDetails.get(0).getId();
                boolean isActive = listOfLoginDetails.get(0).isActive();

                loginDetails.setId(id);
                loginDetails.setMobileNumber(loginDetailsDto.getMobileNumber());
                loginDetails.setLoginType(loginDetailsDto.getLoginType());
                loginDetails.setEmailId(loginDetailsDto.getEmailId());
                loginDetails.setDeviceType(loginDetailsDto.getDeviceType());
                loginDetails.setDeviceId(loginDetailsDto.getDeviceId());
                loginDetails.setClientCode(listOfLoginDetails.get(0).getClientCode());
                loginDetails.setLogin(true);

                if(loginDetailsDto.getDeviceId().equalsIgnoreCase(listOfLoginDetails.get(0).getDeviceId())){
                    if(!isActive){
                        loginDetails.setNewLogin(false);
                        loginDetails.setActive(false);
                        loginDetails.setModifyOn(LocalDateTime.now());
                        loginRepository.save(loginDetails);
                        return new ResponseEntity<>(loginDetails , HttpStatus.OK);
                    }else {
                        loginDetails.setNewLogin(false);
                        loginDetails.setActive(true);
                        loginDetails.setModifyOn(LocalDateTime.now());
                        loginRepository.save(loginDetails);
                        return new ResponseEntity<>(loginDetails , HttpStatus.OK);
                    }
                }else{
                    loginDetails.setNewLogin(false);
                    loginDetails.setActive(false);
                    loginDetails.setModifyOn(LocalDateTime.now());
                    loginRepository.save(loginDetails);
                    return new ResponseEntity<>(loginDetails , HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            throw new SystemErrorException(ERROR,e.getLocalizedMessage());
        }
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
        try {
            String message = "";
            HashMap<String,Object> objMap = new HashMap<>();
            LoginDetails loginDetails = (LoginDetails) object;
            objMap.put(MOBILENUMBER,loginDetails.getMobileNumber());
            objMap.put(EMAILID,loginDetails.getEmailId());
            objMap.put(LOGINTYPE,loginDetails.getLoginType());
            objMap.put(ISACTIVE,loginDetails.isActive());
            if(!loginDetails.isActive()){
                objMap.put(NEXTPAGE,OTPPAGE);
                message = INSERTMESSAGE;
            }else {
                objMap.put(NEXTPAGE,MPIN);
                message = LOGINMESSAGE;
            }
            ResponseModel responseModel = new ResponseModel(SUCCESS,message,objMap);
            return new ResponseEntity<>(responseModel,HttpStatus.OK);
        } catch (Exception e) {
            throw new SystemErrorException(ERROR,e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> processRequest(LoginService loginService, RequestModel requestModel) {
        try {
            loginService.validateRequest(requestModel);
            ResponseEntity<?> prepareRequest = loginService.prepareRequest(requestModel);
            ResponseEntity<?> executeRequest = loginService.executeRequest(requestModel,prepareRequest.getBody());
            return loginService.finalResponse(requestModel,executeRequest.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
