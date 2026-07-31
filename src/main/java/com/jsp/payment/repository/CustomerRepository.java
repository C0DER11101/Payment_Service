package com.jsp.payment.repository;

import com.jsp.payment.dto.CustomerDTO;

import java.math.BigInteger;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.*;
import java.util.List;

public class CustomerRepository {
    // A URL consists of protocol + host + port number + data
    private static final String URL = "jdbc:mysql://localhost:3306/m4_config"; /* protocol is jdbc:mysql, host is localhost, port number is 3306 and data is m4_config (database name) */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "r00t_3nj0y1n9_my$ql";

    public void save(String insertQuery) {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            connection.createStatement().executeUpdate(insertQuery);
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet findAll(String selectQuery) {
        ResultSet result = null;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            result = conn.createStatement().executeQuery(selectQuery);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet findById(String selectQuery) {
        ResultSet result = null;
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            result = conn.createStatement().executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void updatePhoneNumberByID(String updateQuery) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.createStatement().executeUpdate(updateQuery);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}