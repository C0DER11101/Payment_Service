package com.jsp.payment.controller;

import com.jsp.payment.dto.AccountDTO;
import com.jsp.payment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

@Scope("singleton") // this is the default if we don't provide any scope: singleton means only one object will be created and no new objects will be created
//@Scope("prototype") // it will create a new object of AccountController everytime we call getBean() in App.java
@Component // tells spring-core to create an object this class
public class AccountController {

    public AccountController() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }

    @Autowired // create an instance of AccountService and store it in Context (that's why we require spring-context);
    AccountService accountService; // this annotation is used when we want to inject a dependency to a class, this is known as dependency injection.

    // Dependency injection can be done in:
    // 1. Field -> use @Autowired above the target property
    // 2. Setter -> use @Autowired above the required setter
    // 3. Constructors -> create a parameterized constructor that accepts a reference of the target property

    /*
    public static void createConnPool() {
        AccountService.processCreateConnectionPool();
    }
     */

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