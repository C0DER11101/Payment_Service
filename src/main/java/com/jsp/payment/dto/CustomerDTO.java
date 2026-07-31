package com.jsp.payment.dto;

import java.math.BigInteger;

// table name: tx_customer

public class CustomerDTO {
    private BigInteger altKey;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String pinCode;
    private String kycStatus;

    public void setAltKey(BigInteger altKey) {
        this.altKey = altKey;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public BigInteger getAltKey() {
        return altKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "altKey=" + altKey +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", kycStatus='" + kycStatus + '\'' +
                '}';
    }
}