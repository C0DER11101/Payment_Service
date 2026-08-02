package com.jsp.payment.controller;

import com.jsp.payment.dto.AccountDTO;
import com.jsp.payment.service.AccountService;

import java.sql.SQLException;
import java.util.Map;

public class AccountController {
    AccountService accountService = new AccountService();

    public static void createConnPool() {
        AccountService.processCreateConnectionPool();
    }

    public void createAccount(Map<String, Object> accountMap) {
        //keys: customerId, accountType, ifscCode
        accountService.processCreateAccount(accountMap);
    }

    public AccountDTO getAccount(String accountNumber) throws SQLException {
        return accountService.processGetAccountByAccountNumber(accountNumber);
    }

    public void updateBal(String accountNumber, double balance) {
        accountService.processUpdateBalance(accountNumber, balance);
    }
}