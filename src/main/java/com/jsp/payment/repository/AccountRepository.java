package com.jsp.payment.repository;

import com.jsp.payment.util.SequenceGeneratorUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountRepository {
    private final static int MAX_CONN = 100;
    private static List<Connection> pool = new ArrayList<>(MAX_CONN);
    private static final String URL = "jdbc:mysql://localhost:3306/m4_config"; /* protocol is jdbc:mysql, host is localhost, port number is 3306 and data is m4_config (database name) */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "r00t_3nj0y1n9_my$ql";

    private final String CREATE_ACCOUNT_QUERY =
            "insert into tx_account(`alt_key`, `customer_id`, `account_number`, `account_type`, `account_status`, " +
                    "`bank_name`, `ifsc_code`, `balance`)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?)";

    private final String ACCOUNT_REQUEST_QUERY =
            "select * from tx_account"+
                    " where account_number = ?";

    private final String UPDATE_BALANCE_PROC = "call UpdateBalance(?, ?)";

    public static void createConnectionPool() {
        for(int i = 1; i <= MAX_CONN; i++) {
            try {
                pool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(Map<String, Object> accountMap, String accountNumber) {
        // account_status=ACTIVE
        // balance=0

        try {
            Connection conn = AccountRepository.pool.get(0);
            PreparedStatement preparedQuery = conn.prepareStatement(CREATE_ACCOUNT_QUERY);
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
            Connection conn = AccountRepository.pool.get(0);
            PreparedStatement preparedQuery = conn.prepareStatement(ACCOUNT_REQUEST_QUERY);
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
            Connection conn = AccountRepository.pool.get(0);
            CallableStatement statement = conn.prepareCall(UPDATE_BALANCE_PROC);
            statement.setString("account_number", accountNumber);
            statement.setDouble("balance", balance);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}