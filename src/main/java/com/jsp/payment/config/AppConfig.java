package com.jsp.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
info of our project that we provide to an external library
is called configuration.
 */

@EnableTransactionManagement // -> enables transaction management so that the data gets reflected in the table
@Configuration // -> that's why we provide this annotation
@ComponentScan(basePackages = "com.jsp.payment")
public class AppConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/m4_config"; /* protocol is jdbc:mysql, host is localhost, port number is 3306 and data is m4_config (database name) */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "r00t_3nj0y1n9_my$ql";

    @Bean
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}