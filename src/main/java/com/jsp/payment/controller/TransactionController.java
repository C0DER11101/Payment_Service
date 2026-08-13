package com.jsp.payment.controller;

import com.jsp.payment.dto.WithdrawDTO;
import com.jsp.payment.model.TransactionModel;
import com.jsp.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.payment.dto.TransactionDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TransactionController {

    @Autowired
    TransactionService txService;

    /*
    public TransactionController() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }
     */

    public void createTransaction(TransactionDTO txDto) {
        txService.processTransaction(txDto);
    }

    public TransactionModel getById(BigInteger altKey) {
        return txService.processFindById(altKey);
    }

    public List<TransactionModel> getAll() {
        return txService.processFindAll();
    }

    public List<TransactionModel> getTransactionByPaymentMode(String paymentMode) {
        return txService.processGetTransactionByPaymentMode(paymentMode);
    }

    public void updateTransactionStatusById(Map<String, Object> map) {
        txService.processUpdateStatusById(map);
    }

    public void withdrawAmount(WithdrawDTO withdrawalAmount) {
        txService.processWithdrawAmount(withdrawalAmount);
    }

    public void getByTxStatus(String status) {
        txService.processFindByTxStatus(status);
    }
}