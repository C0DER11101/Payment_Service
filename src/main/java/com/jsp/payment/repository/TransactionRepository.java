package com.jsp.payment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepository {

    public TransactionRepository() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }
}