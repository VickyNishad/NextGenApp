package com.nextgen.login.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Login_Master")
public class LoginDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "ClientCode")
    private String clientCode;

    @Column(name = "MobileNumber")
    private String mobileNumber;

    @Column(name = "EmailId")
    private String emailId;

    @Column(name = "DeviceId")
    private String deviceId;

    @Column(name = "DeviceType")
    private String deviceType;

    @Column(name = "LoginType")
    private String loginType;

    @Column(name = "IsActive")
    private boolean isActive;

    @Column(name = "IsLogin")
    private boolean isLogin;

    @Column(name = "IsNewLogin")
    private boolean isNewLogin;

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

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isNewLogin() {
        return isNewLogin;
    }

    public void setNewLogin(boolean isNewLogin) {
        this.isNewLogin = isNewLogin;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(LocalDateTime modifyOn) {
        this.modifyOn = modifyOn;
    }
}
