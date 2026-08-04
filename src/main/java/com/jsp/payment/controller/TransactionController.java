package com.jsp.payment.controller;

import com.jsp.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionController {

    @Autowired
    TransactionService txService;

    public TransactionController() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }
}