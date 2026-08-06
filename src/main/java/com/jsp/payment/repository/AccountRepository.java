package com.jsp.payment.repository;

import com.jsp.payment.util.SequenceGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AccountRepository {
    private final static int MAX_CONN = 100;
    private static List<Connection> pool = new ArrayList<>(MAX_CONN);

    @Autowired
    Connection connection;

    private final String CREATE_ACCOUNT_QUERY =
            "insert into tx_account(`alt_key`, `customer_id`, `account_number`, `account_type`, `account_status`, " +
                    "`bank_name`, `ifsc_code`, `balance`)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?)";

    private final String ACCOUNT_REQUEST_QUERY =
            "select * from tx_account"+
                    " where account_number = ?";

    private final String UPDATE_BALANCE_PROC = "call UpdateBalance(?, ?)";

    public AccountRepository() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }

    /*
    public static void createConnectionPool() {
        for(int i = 1; i <= MAX_CONN; i++) {
            try {
                pool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
     */

    public void save(Map<String, Object> accountMap, String accountNumber) {
        // account_status=ACTIVE
        // balance=0

        try {
            PreparedStatement preparedQuery = connection.prepareStatement(CREATE_ACCOUNT_QUERY);
            preparedQuery.setInt(1, SequenceGeneratorUtil.generateAltKey().intValue());
            preparedQuery.setString(2, accountMap.get("customerId").toString());
            preparedQuery.setString(3, accountNumber);
            preparedQuery.setString(4, accountMap.get("accountType").toString());
            preparedQuery.setString(5, "ACTIVE");
            preparedQuery.setString(6, "bankName");
            preparedQuery.setString(7, accountMap.get("ifscCode").toString());
            preparedQuery.setDouble(8, 0);

            preparedQuery.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAccountByAccountNumber(String accountNumber) {
        ResultSet result = null;
        try {
            PreparedStatement preparedQuery = connection.prepareStatement(ACCOUNT_REQUEST_QUERY);
            preparedQuery.setString(1, accountNumber); /* since there is only one ? in the ACCOUNT_REQUEST_QUERY,
            we are passing the parameter index as 1, the parameter index represents which ? we are referring to in the query */

            result = preparedQuery.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void updateBalance(String accountNumber, double balance) {
        try {
            CallableStatement statement = connection.prepareCall(UPDATE_BALANCE_PROC);
            statement.setString("account_number", accountNumber);
            statement.setDouble("balance", balance);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}