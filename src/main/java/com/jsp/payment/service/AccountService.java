package com.jsp.payment.service;

import com.jsp.payment.repository.AccountRepository;
import com.jsp.payment.util.SequenceGeneratorUtil;

import java.util.Map;

public class AccountService {
    AccountRepository accountRepo = new AccountRepository();
    public void processCreateAccount(Map<String, Object> accountMap) {
        // account_number: customerId_ifscCode_randomNumber

        String accountNumber = accountMap.get("customerId").toString() + "_" +
                accountMap.get("ifscCode").toString() + "_" +
                SequenceGeneratorUtil.randomNum();

        accountRepo.save(accountMap, accountNumber);
    }
}