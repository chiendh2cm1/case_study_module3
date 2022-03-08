package com.codegym.model;

public class Account {
    private String accountId;
    private String accountName;
    private String loginName;
    private String accountAccess;
    private String password;
    private String address;
    private String phoneNumber;
    private boolean gender;
    private boolean status;

    public Account() {
    }

    public Account(String accountId, String accountName, String loginName, String password, String accountAccess, String address, String phoneNumber, boolean gender, boolean status) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.loginName = loginName;
        this.accountAccess = accountAccess;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAccountAccess() {
        return accountAccess;
    }

    public void setAccountAccess(String accountAccess) {
        this.accountAccess = accountAccess;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}