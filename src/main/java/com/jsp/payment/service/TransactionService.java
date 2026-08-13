package com.jsp.payment.service;

import com.jsp.payment.controller.AccountController;
import com.jsp.payment.dto.AccountDTO;
import com.jsp.payment.dto.TransactionDTO;
import com.jsp.payment.dto.WithdrawDTO;
import com.jsp.payment.model.TransactionModel;
import com.jsp.payment.repository.TransactionRepository;
import com.jsp.payment.util.SequenceGeneratorUtil;
import com.jsp.payment.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository txRepo;

    @Autowired
    private AccountController accCtrl;

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

    public List<TransactionModel> processGetTransactionByPaymentMode(String paymentMode) {
        return txRepo.findByPaymentMode(paymentMode);
    }

    public void processUpdateStatusById(Map<String, Object> map) {
        Set<String> keys = map.keySet();

        for(String key : keys)
            txRepo.updateStatusByTransactionId(key, (String)map.get(key));
    }

    public void processWithdrawAmount(WithdrawDTO withdrawalAmount) {
        try {
            // fetch balance from tx_account
            AccountDTO accDto = accCtrl.getAccount(withdrawalAmount.getFromAccountNumber());
            Double balance = accDto.getBalance();

            if(balance.compareTo(withdrawalAmount.getAmount()) >= 0) {
                accDto.setBalance(balance - withdrawalAmount.getAmount());
                accCtrl.updateBal(accDto.getAccountNumber(), accDto.getBalance());
                TransactionModel txModel = new TransactionModel();
                txModel.setAltKey(SequenceGeneratorUtil.generateAltKey());
                txModel.setTransactionId(TransactionUtil.getTransactionId());
                txModel.setFromAccount(withdrawalAmount.getFromAccountNumber());
                txModel.setToAccount(withdrawalAmount.getToAccountNumber());
                txModel.setAmount(withdrawalAmount.getAmount());
                txModel.setTxType(withdrawalAmount.getTxType());
                txModel.setPaymentMode(withdrawalAmount.getPaymentMode());
                txModel.setStatus("in progress");
                txModel.setRemarks("Successful");
                txModel.setTransactionDate(new Date());

                txRepo.save(txModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processFindByTxStatus(String status) {
        List<BigInteger> txAltKeyList = txRepo.findByTxStatus(status).stream()
                .map(a -> a.getAltKey())
                .collect(Collectors.toList());
        txRepo.updateByTxStatus(txAltKeyList);
    }
}