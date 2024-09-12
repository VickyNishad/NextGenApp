package com.nextgen.sms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextgen.sms.constant.EmailSMSConstant;
import com.nextgen.sms.dto.EmailSMSDetailsDto;
import com.nextgen.sms.exception.RequestNotFoundException;
import com.nextgen.sms.exception.SystemErrorException;
import com.nextgen.sms.models.EmailSMSDetails;
import com.nextgen.sms.models.RequestModel;
import com.nextgen.sms.models.ResponseModel;
import com.nextgen.sms.repository.EmailSMSDetailsRepository;
import com.nextgen.sms.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class SubmitEmailSMSDetailsServiceImpl implements EmailSMSService, EmailSMSConstant {
    @Autowired
    private EmailSMSDetailsRepository emailSMSDetailsRepository;
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

                    if(payloadRequest.containsKey(UNIQUECODETYPE)){
                        if(String.valueOf(payloadRequest.get(UNIQUECODETYPE)).isBlank() || String.valueOf(payloadRequest.get(UNIQUECODETYPE)).isEmpty()){
                            throw new RequestNotFoundException(ERROR,"UniqueTypeCode"+ERRORMESSAGE);
                        }else{
                            if (String.valueOf(payloadRequest.get(UNIQUECODETYPE)).equalsIgnoreCase(MOBILE)){
                                if(payloadRequest.containsKey(MOBILENUMBER)){
                                    if(String.valueOf(payloadRequest.get(MOBILENUMBER)).isBlank() ||String.valueOf(payloadRequest.get(MOBILENUMBER)).isEmpty() ){
                                        throw new RequestNotFoundException(ERROR,"MobileNumber"+ERRORMESSAGE);
                                    }
                                }else {
                                    throw new RequestNotFoundException(ERROR,MOBILENUMBER+FIELDERRORMESSAGE);
                                }
                            }

                            if (String.valueOf(payloadRequest.get(UNIQUECODETYPE)).equalsIgnoreCase(EMAIL)){
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
                        throw new RequestNotFoundException(ERROR,UNIQUECODETYPE+FIELDERRORMESSAGE);
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
            EmailSMSDetailsDto emailSMSDetailsDto = new EmailSMSDetailsDto();
            List<HashMap<String,Object>> listOfRequest = requestModel.getPayload();
            for (HashMap<String,Object> payloadRequest : listOfRequest){
                emailSMSDetailsDto.setMobileNumber(String.valueOf(payloadRequest.get(MOBILENUMBER)));
                emailSMSDetailsDto.setUniqueCodeType(String.valueOf(payloadRequest.get(UNIQUECODETYPE)));
                emailSMSDetailsDto.setDeviceId(String.valueOf(payloadRequest.get(DEVICEID)));
                emailSMSDetailsDto.setDeviceType(String.valueOf(payloadRequest.get(DEVICETYPE)));
                emailSMSDetailsDto.setEmailId(String.valueOf(payloadRequest.get(EMAILID)));
                emailSMSDetailsDto.setUniqueCode(Utils.generateOtp());
            }
            return  new ResponseEntity<>(emailSMSDetailsDto,HttpStatus.OK);
        } catch (Exception e) {
            throw new SystemErrorException(ERROR,e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> executeRequest(RequestModel requestModel, Object object) {
        try {
            EmailSMSDetailsDto emailSMSDetailsDto = (EmailSMSDetailsDto) object;
            EmailSMSDetails emailSMSDetails = new EmailSMSDetails();
            List<EmailSMSDetails> listOfOptDetails = emailSMSDetailsRepository.findByMobileNumber(emailSMSDetailsDto.getMobileNumber());
            if(listOfOptDetails==null || listOfOptDetails.isEmpty()){
                emailSMSDetails = new EmailSMSDetails();
                emailSMSDetails.setUniqueCode(emailSMSDetailsDto.getUniqueCode());
                emailSMSDetails.setMobileNumber(emailSMSDetailsDto.getMobileNumber());
                emailSMSDetails.setUniqueCodeType(emailSMSDetailsDto.getUniqueCodeType());
                emailSMSDetails.setDeviceId(emailSMSDetailsDto.getDeviceId());
                emailSMSDetails.setDeviceType(emailSMSDetailsDto.getDeviceType());
                emailSMSDetails.setEmailId(emailSMSDetailsDto.getEmailId());
                emailSMSDetails.setVerified(false);
                emailSMSDetails.setCreatedOn(LocalDateTime.now());
            }else {
                long id = listOfOptDetails.get(0).getId();
                emailSMSDetails = new EmailSMSDetails();
                emailSMSDetails.setId(id);
                emailSMSDetails.setUniqueCode(emailSMSDetailsDto.getUniqueCode());
                emailSMSDetails.setMobileNumber(emailSMSDetailsDto.getMobileNumber());
                emailSMSDetails.setUniqueCodeType(emailSMSDetailsDto.getUniqueCodeType());
                emailSMSDetails.setDeviceId(emailSMSDetailsDto.getDeviceId());
                emailSMSDetails.setDeviceType(emailSMSDetailsDto.getDeviceType());
                emailSMSDetails.setEmailId(emailSMSDetailsDto.getEmailId());
                emailSMSDetails.setVerified(false);
                emailSMSDetails.setModifyOn(LocalDateTime.now());
            }
            emailSMSDetailsRepository.save(emailSMSDetails);
            ResponseModel responseModel = new ResponseModel(SUCCESS,OTPSENTSUCCESSFULLY, emailSMSDetails);
            return  new ResponseEntity<>(responseModel ,HttpStatus.OK);
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
    public ResponseEntity<?> finalResponse(RequestModel requestModel,ResponseModel responseModel) {
        try {
            ResponseModel response = null;
            HashMap<String,Object> objMap = new HashMap<>();
            if(responseModel.getMessage().equalsIgnoreCase(OTPSENTSUCCESSFULLY)){
                EmailSMSDetails emailSMSDetails = (EmailSMSDetails) responseModel.getResponseData();
                objMap.put(MOBILENUMBER, emailSMSDetails.getMobileNumber());
                objMap.put(EMAILID, emailSMSDetails.getEmailId());
                objMap.put(UNIQUECODETYPE, emailSMSDetails.getUniqueCodeType());
                objMap.put(ISVERIFIED, emailSMSDetails.isVerified());
                objMap.put(NEXTPAGE,VERIFY);
                response = new ResponseModel(SUCCESS,OTPSENTSUCCESSFULLY,objMap);
            }else{
                response = new ResponseModel(ERROR,OTPINSERTERROR,objMap);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            throw new SystemErrorException(ERROR,e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> processRequest(EmailSMSService emailSMSService, RequestModel requestModel) {
        ResponseEntity<?> validateRequest = emailSMSService.validateRequest(requestModel);
        ResponseEntity<?> prepareRequest = emailSMSService.prepareRequest(requestModel);
        ResponseEntity<?> execute = emailSMSService.executeRequest(requestModel ,prepareRequest.getBody());
        ResponseModel responseModel = (ResponseModel) execute.getBody();
        ResponseEntity<?> finalResponse = emailSMSService.finalResponse(requestModel,responseModel);
        return new ResponseEntity<>(finalResponse.getBody(),HttpStatus.OK);
    }
}
