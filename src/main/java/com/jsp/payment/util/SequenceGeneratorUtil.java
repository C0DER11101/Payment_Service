package com.jsp.payment.util;

import java.math.BigInteger;

public class SequenceGeneratorUtil {
    public static BigInteger generateAltKey() {
        return BigInteger.valueOf(100 + (long)(Math.random() * 45000)); // generate random number between 100 and 450099
    }

    public static BigInteger randomNum() {
        return BigInteger.valueOf(100 + (long)(Math.random() * 450)); // generate random number
    }
}