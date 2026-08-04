package com.jsp.payment.service;

import com.jsp.payment.dto.AccountDTO;
import com.jsp.payment.repository.AccountRepository;
import com.jsp.payment.util.SequenceGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.math.BigInteger;

@Component
public class AccountService {

    public AccountService() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }

    @Autowired
    AccountRepository accountRepo;

    public static void processCreateConnectionPool() {
        AccountRepository.createConnectionPool();
    }

    public void processCreateAccount(Map<String, Object> accountMap) {
        // account_number: customerId_ifscCode_randomNumber

        String accountNumber = accountMap.get("customerId").toString() + "_" +
                accountMap.get("ifscCode").toString() + "_" +
                SequenceGeneratorUtil.randomNum();

        accountRepo.save(accountMap, accountNumber);
    }

    public AccountDTO processGetAccountByAccountNumber(String accountNumber) throws SQLException {
        ResultSet result = accountRepo.getAccountByAccountNumber(accountNumber);

        if(result == null)
            return null;

        AccountDTO accountDto = new AccountDTO();
        while(result.next()) {
            accountDto.setAltKey(BigInteger.valueOf(result.getInt(1)));
            accountDto.setCustomerId(BigInteger.valueOf(result.getInt(2)));
            accountDto.setAccountNumber(result.getString(3));
            accountDto.setAccountType(result.getString(4));
            accountDto.setAccountStatus(result.getString(5));
            accountDto.setBankName(result.getString(6));
            accountDto.setIfscCode(result.getString(7));
            accountDto.setBalance(result.getDouble(8));
        }

        return accountDto;
    }

    public void processUpdateBalance(String accountNumber, double balance) {
        accountRepo.updateBalance(accountNumber, balance);
    }
}