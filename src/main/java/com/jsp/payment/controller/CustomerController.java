package com.jsp.payment.controller;

import com.jsp.payment.dto.CustomerDTO;
import com.jsp.payment.service.CustomerService;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import java.sql.ResultSet;

public class CustomerController {
    CustomerService customerService = new CustomerService();

    public void register(CustomerDTO customerDto) {
        customerService.processRegister(customerDto);
    }

    public List<CustomerDTO> getAllCustomers() throws SQLException {
        return customerService.processGetCustomers();
    }

    public CustomerDTO getCustomerByID(BigInteger altKey) throws SQLException {
        return customerService.processGetCustomerByID(altKey);
    }

    public void updatePhoneNumber(BigInteger altKey, String ph) {
        customerService.processUpdatePhoneNumberByID(altKey, ph);
    }
}