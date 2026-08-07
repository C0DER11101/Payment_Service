package com.jsp.payment.dto;

import java.math.BigInteger;
import java.util.Date;

// table name: tx_transaction

public class TransactionDTO {

    private String fromAccount;
    private String toAccount;
    private Double amount;
    private String txType;
    private String paymentMode;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String
    toString() {
        return "TransactionDTO{" +
                "fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", txType='" + txType + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}