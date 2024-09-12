package com.nextgen.sms.dto;


public class EmailSMSDetailsDto {

    private Long id;
    private int uniqueCode;
    private String mobileNumber;
    private String emailId;
    private String deviceId;
    private String deviceType;
    private String uniqueCodeType;
    private boolean isVerified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUniqueCode(int uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public int getUniqueCode() {
        return uniqueCode;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getEmailId() {
        return emailId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getUniqueCodeType() {
        return uniqueCodeType;
    }

    public void setUniqueCodeType(String uniqueCodeType) {
        this.uniqueCodeType = uniqueCodeType;
    }

    public boolean isVerified(boolean b) {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

}
