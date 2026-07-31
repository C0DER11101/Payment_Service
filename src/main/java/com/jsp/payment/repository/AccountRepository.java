package com.jsp.payment.repository;

import com.jsp.payment.util.SequenceGeneratorUtil;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class AccountRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/m4_config"; /* protocol is jdbc:mysql, host is localhost, port number is 3306 and data is m4_config (database name) */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "r00t_3nj0y1n9_my$ql";

    private final String CREATE_ACCOUNT_QUERY =
            "insert into tx_account(`alt_key`, `customer_id`, `account_number`, `account_type`, `account_status`, " +
                    "`bank_name`, `ifsc_code`, `balance`)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?)";

    public void save(Map<String, Object> accountMap, String accountNumber) {
        // account_status=ACTIVE
        // balance=0

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
}