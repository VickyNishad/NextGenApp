package com.nextgen.sms.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Email_SMS_OTP_Master")
public class EmailSMSDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "UniqueCode")
    private int uniqueCode;
    @Column(name = "MobileNumber")
    private String mobileNumber;
    @Column(name = "EmailId")
    private String emailId;
    @Column(name = "DeviceId")
    private String deviceId;
    @Column(name = "DeviceType")
    private String deviceType;
    @Column(name = "UniqueCodeType")
    private String uniqueCodeType;
    @Column(name = "IsVerified")
    private boolean isVerified;
    @Column(name = "CreatedOnDate")
    private LocalDateTime createdOn;
    @Column(name = "ModifyOnDate")
    private LocalDateTime modifyOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUniqueCode(int uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public int getUniqueCode(int otpDetails) {
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setModifyOn(LocalDateTime modifyOn) {
        this.modifyOn = modifyOn;
    }
    public LocalDateTime getModifyOn() {
        return modifyOn;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

}
