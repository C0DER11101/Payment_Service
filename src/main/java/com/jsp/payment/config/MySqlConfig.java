package com.jsp.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MySqlConfig {

    @Bean
    public DataSource getDataSource() { // creates a connection
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("r00t_3nj0y1n9_my$ql");
        dataSource.setUrl("jdbc:mysql://localhost:3306/m4_config");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() { // manages connections, implements EntityManagerFactory
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource()); // set the data source to get the connection to the database
        factoryBean.setPackagesToScan("com.jsp.payment.model"); // look for the Entity class in the provided package path
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProps = new Properties();
        jpaProps.put("hibernate.show_sql", "true"); // properties are stored as key-value pairs
        factoryBean.setJpaProperties(jpaProps);

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager getTxManager() { // JpaTransaction is used for managing transactions
        /*
        A transaction is a series of multiple DB operations.
        If even one DB operation fails, all the DB operations performed before it are rolled back!
         */

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());

        return transactionManager;
    }
}