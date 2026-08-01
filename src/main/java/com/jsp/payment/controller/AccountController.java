package com.jsp.payment.controller;

import com.jsp.payment.dto.AccountDTO;
import com.jsp.payment.service.AccountService;

import java.sql.SQLException;
import java.util.Map;

public class AccountController {
    AccountService accountService = new AccountService();
    public void createAccount(Map<String, Object> accountMap) {
        //keys: customerId, accountType, ifscCode
        accountService.processCreateAccount(accountMap);
    }

    public AccountDTO getAccount(String accountNumber) throws SQLException {
        return accountService.processGetAccountByAccountNumber(accountNumber);
    }
}