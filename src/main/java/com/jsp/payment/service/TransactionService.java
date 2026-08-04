package com.jsp.payment.service;

import com.jsp.payment.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    @Autowired
    TransactionRepository txRepo;

    public TransactionService() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }
}