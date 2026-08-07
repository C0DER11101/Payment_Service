package com.jsp.payment.service;

import com.jsp.payment.dto.TransactionDTO;
import com.jsp.payment.model.TransactionModel;
import com.jsp.payment.repository.TransactionRepository;
import com.jsp.payment.util.SequenceGeneratorUtil;
import com.jsp.payment.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Component
public class TransactionService {

    @Autowired
    TransactionRepository txRepo;

    public TransactionService() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }

    public void processTransaction(TransactionDTO txDto) {
        TransactionModel txModel = new TransactionModel();
        txModel.setAltKey(SequenceGeneratorUtil.generateAltKey());
        txModel.setTransactionId(TransactionUtil.getTransactionId());
        txModel.setFromAccount(txDto.getFromAccount());
        txModel.setToAccount(txDto.getToAccount());
        txModel.setAmount(txDto.getAmount());
        txModel.setTxType(txDto.getTxType());
        txModel.setPaymentMode(txDto.getPaymentMode());
        txModel.setStatus("in progress");
        txModel.setRemarks(null);
        txModel.setTransactionDate(new Date());

        txRepo.save(txModel);
    }

    public TransactionModel processFindById(BigInteger altKey) {
        return txRepo.findById(altKey);
    }

    public List<TransactionModel> processFindAll() {
        return txRepo.findAll();
    }
}