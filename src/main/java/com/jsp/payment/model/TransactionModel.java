package com.jsp.payment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigInteger;
import java.util.Date;

@Entity // means that TransactionModel can be mapped to a table
@Table(name = "tx_transaction") // specifies the name of the database table that TransactionModel maps to
public class TransactionModel {

    @Id // marks altKey as the primary key; if not provided then AnnotationException will be thrown
    @Column(name = "alt_key") // specifies altKey property maps to the column alt_key in the tx_transaction table
    private BigInteger altKey; // primary key

    @Column(name = "transaction_id")
    private String transactionId; // must be a unique column

    @Column(name = "from_account")
    private String fromAccount;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "tx_type")
    private String txType;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "status")
    private String status; // set to "in progress"

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "transaction_date")
    private Date transactionDate;

    public BigInteger getAltKey() {
        return altKey;
    }

    public void setAltKey(BigInteger altKey) {
        this.altKey = altKey;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "altKey=" + altKey +
                ", transactionId='" + transactionId + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", txType='" + txType + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}