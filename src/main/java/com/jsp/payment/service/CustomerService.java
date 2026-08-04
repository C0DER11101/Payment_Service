package com.jsp.payment.service;

import com.jsp.payment.dto.CustomerDTO;
import com.jsp.payment.repository.CustomerRepository;
import com.jsp.payment.util.SequenceGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    private final String CUSTOMER_INSERT_QUERY =
            "insert into tx_customer" +
                    "(`alt_key`, `first_name`, `last_name`, `email`, `phone_number`, `pincode`, `kyc_status`)" +
                    " values(";
    private final String CUSTOMER_SELECT_QUERY =
            "select * from tx_customer";

    private final String CUSTOMER_SELECT_BY_ID_QUERY =
            "select * from tx_customer" +
                    " where alt_key = ";

    private final String CUSTOMER_UPDATE_PH_BY_ID_QUERY =
            "update tx_customer set phone_number = ";

    private final String WHERE_CLAUSE_ALTKEY =
            "where alt_key = ";

    public CustomerService() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }

    public void processRegister(CustomerDTO customerDto) {
        String insertQuery = prepareInsertQuery(customerDto);
        customerRepo.save(insertQuery);
    }

    public String prepareInsertQuery(CustomerDTO customerDto) {
        StringBuilder builder = new StringBuilder();
        builder.append(CUSTOMER_INSERT_QUERY);
        builder.append(SequenceGeneratorUtil.generateAltKey());
        builder.append(", ");
        builder.append("'" + customerDto.getFirstName() + "', ");
        builder.append("'" + customerDto.getLastName() + "', ");
        builder.append("'" + customerDto.getEmail() + "', ");
        builder.append("'" + customerDto.getPhoneNumber() + "', ");
        builder.append("'" + customerDto.getPinCode() + "', ");
        builder.append("'" + customerDto.getKycStatus() + "')");

        return builder.toString();
    }

    public List<CustomerDTO> processGetCustomers() throws SQLException {
        List<CustomerDTO> customerDtoList = new ArrayList<>();
        ResultSet result = customerRepo.findAll(CUSTOMER_SELECT_QUERY);

        while(result.next()) {
            CustomerDTO customerDto = new CustomerDTO();
            customerDto.setAltKey(new BigInteger(result.getString("alt_key")));
            customerDto.setFirstName(result.getString("first_name"));
            customerDto.setLastName(result.getString("last_name"));
            customerDto.setEmail(result.getString("email"));
            customerDto.setPhoneNumber(result.getString("phone_number"));
            customerDto.setPinCode(result.getString("pincode"));
            customerDto.setKycStatus(result.getString("kyc_status"));

            customerDtoList.add(customerDto);
        }

        return customerDtoList;
    }

    public CustomerDTO processGetCustomerByID(BigInteger altKey) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append(CUSTOMER_SELECT_BY_ID_QUERY);
        query.append(altKey);
        ResultSet result = customerRepo.findById(query.toString());

        CustomerDTO customerDto = new CustomerDTO();

        while(result.next()) {
            customerDto.setAltKey(new BigInteger(result.getString("alt_key")));
            customerDto.setFirstName(result.getString("first_name"));
            customerDto.setLastName(result.getString("last_name"));
            customerDto.setEmail(result.getString("email"));
            customerDto.setPhoneNumber(result.getString("phone_number"));
            customerDto.setPinCode(result.getString("pincode"));
            customerDto.setKycStatus(result.getString("kyc_status"));
        }

        return customerDto;
    }

    public void processUpdatePhoneNumberByID(BigInteger altKey, String ph) {
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append(CUSTOMER_UPDATE_PH_BY_ID_QUERY);
        updateQuery.append("'" + ph + "'");
        updateQuery.append(WHERE_CLAUSE_ALTKEY);
        updateQuery.append(altKey);

        customerRepo.updatePhoneNumberByID(updateQuery.toString());
    }

}