package com.jsp.payment.dto;

import java.math.BigInteger;

// table name: tx_account

public class AccountDTO {
    private BigInteger altKey;
    private BigInteger customerId; // must be unique column
    private String accountNumber; // must be unique column
    private String accountType;
    private String accountStatus;
    private String bankName;
    private String ifscCode;
    private Double balance;

    public BigInteger getAltKey() {
        return altKey;
    }

    public void setAltKey(BigInteger altKey) {
        this.altKey = altKey;
    }

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "altKey=" + altKey +
                ", customerId=" + customerId +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", bankName='" + bankName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", balance=" + balance +
                '}';
    }
}