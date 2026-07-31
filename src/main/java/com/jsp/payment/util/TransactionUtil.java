package com.jsp.payment.util;

import java.math.BigInteger;

public class TransactionUtil {
    public static String getTransactionId() {
        BigInteger random = BigInteger.valueOf(600000 + (long)(Math.random() * 345000058));

        return random.toString();
    }

    public static BigInteger getCustomerId() {
        return BigInteger.valueOf(708907 + (long)(Math.random() * 779651342));
    }
}