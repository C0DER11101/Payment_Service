package com.jsp.payment.controller;

import com.jsp.payment.service.AccountService;

import java.util.Map;

public class AccountController {
    AccountService accountService = new AccountService();
    public void createAccount(Map<String, Object> accountMap) {
        //keys: customerId, accountType, ifscCode
        accountService.processCreateAccount(accountMap);
    }
}