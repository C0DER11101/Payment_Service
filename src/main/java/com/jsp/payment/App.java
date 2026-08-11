package com.jsp.payment;

import com.jsp.payment.controller.AccountController;
import com.jsp.payment.controller.CustomerController;
import com.jsp.payment.controller.TransactionController;
import com.jsp.payment.dto.AccountDTO;
import com.jsp.payment.dto.CustomerDTO;
import com.jsp.payment.dto.TransactionDTO;
import com.jsp.payment.model.TransactionModel;
import com.jsp.payment.util.SequenceGeneratorUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.jsp.payment.config.AppConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        //AccountController.createConnPool();

        /*
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("Priyanuj");
        customerDTO1.setLastName("Bora");
        customerDTO1.setEmail("priyanuj1902assam@gmail.com");
        customerDTO1.setPhoneNumber("8486370453");
        customerDTO1.setPinCode("785001");
        customerDTO1.setKycStatus("completed");

        CustomerController customerController = new CustomerController();

        customerController.register(customerDTO1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Animesh");
        customerDTO2.setLastName("Bora");
        customerDTO2.setEmail("bora.animesh2013@gmail.com");
        customerDTO2.setPhoneNumber("9395652428");
        customerDTO2.setPinCode("785001");
        customerDTO2.setKycStatus("incomplete");

        customerController.register(customerDTO2);
         */

        //List<CustomerDTO> list = null;

        /*
        try {
            customerController.getAllCustomers().stream()
                    .peek(System.out::println)
                    .collect(Collectors.toList());
        } catch(SQLException e) {
            e.printStackTrace();
        }
         */

        /*
        try {
            System.out.println(customerController.getCustomerByID(BigInteger.valueOf(6796)));
        } catch(SQLException e) {
            e.printStackTrace();
        }

        customerController.updatePhoneNumber(BigInteger.valueOf(1172), "8876265959");
         */

        //AccountController accountController = new AccountController();

        /*
        CustomerDTO fetchedCustomerDTO = null;

        try {
            fetchedCustomerDTO = customerController.getCustomerByID(BigInteger.valueOf(6796));
        } catch(SQLException e) {
            e.printStackTrace();
        }

        Map<String, Object> accountMap = new HashMap<>();

        accountMap.put("customerId", fetchedCustomerDTO.getAltKey());

        accountMap.put("accountType", "savings");
        accountMap.put("ifscCode", accountMap.get("customerId").toString() + SequenceGeneratorUtil.randomNum());

        accountController.createAccount(accountMap);
         */

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        AccountController accountController = context.getBean(AccountController.class);

        accountController.updateBal("6796_6796433_442", 100000);

        AccountDTO accountDTO = new AccountDTO();

        try {
            accountDTO = accountController.getAccount("6796_6796433_442");
        } catch(SQLException e) {
            e.printStackTrace();
        }

        System.out.println(accountDTO);


        //AccountController accountController = context.getBean(AccountController.class);

        //AccountDTO accountDTO = null;

        /*
        try {
            accountDTO = accountController.getAccount("6796_6796433_442");
        } catch(SQLException e) {
            e.printStackTrace();
        }
         */

        //System.out.println(accountDTO);

        /*
        context.getBean(AccountController.class);
        context.getBean(AccountController.class);
        context.getBean(AccountController.class);
        context.getBean(AccountController.class);
        context.getBean(AccountController.class);
         */

        TransactionController txController = context.getBean(TransactionController.class);

        /*
        TransactionDTO txDto = new TransactionDTO();

        txDto.setFromAccount("6796_6796433_442");
        txDto.setToAccount("2399_3456791_543");
        txDto.setAmount(30000d);
        txDto.setTxType(null);
        txDto.setPaymentMode(null);

        txController.createTransaction(txDto);
         */

        /*
        TransactionModel tm = txController.getById(BigInteger.valueOf(23372));

        System.out.println(tm);

        List<TransactionModel> list = txController.getAll();

        System.out.println(list);

        list.stream()
                .forEach(System.out::println);
         */

        txController.getTransactionByPaymentMode("online").stream()
                .forEach(System.out::println);
    }
}