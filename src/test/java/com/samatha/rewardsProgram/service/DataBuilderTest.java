package com.samatha.rewardsProgram.service;

import com.samatha.rewardsProgram.dto.CustomerDTO;
import com.samatha.rewardsProgram.dto.Rewards;
import com.samatha.rewardsProgram.dto.TransactionDTO;
import com.samatha.rewardsProgram.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataBuilderTest {


    DataBuilder dataBuilder = new DataBuilder();

    private TransactionDTO transactionDTO = new TransactionDTO();

    @BeforeEach
    public void setup() {
        CustomerDTO customer = dataBuilder.constructCustomerDTOWithCustomerName("test");
        transactionDTO.setCustomerDTO(customer);
        transactionDTO.setTransactionDate(LocalDateTime.of(2023,
                Month.APRIL, 1, 19, 30, 40));
        transactionDTO.setTransactionAmount(Long.valueOf(90));
        transactionDTO.setTransactionID(1000l);

    }


    @Test
    void getTransactionsPerCustomer() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(transactionDTO);
        dataBuilder.getTransactionsPerCustomer(transactionDTOList);
        assertEquals(1, dataBuilder.getTransactionsPerCustomer(transactionDTOList).size());
    }

    @Test
    void getCalculateMonthlyRewardsTest() {
        CustomerDTO customer = dataBuilder.constructCustomerDTOWithCustomerName("test");
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(transactionDTO);
        Rewards rewards = dataBuilder.calculateMonthlyRewards(customer, transactionDTOList);
        assertNotNull(rewards);
    }

    @Test
    void constructCustomerDTOTest() {
        Customer customer = new Customer();
        CustomerDTO customerDTO = dataBuilder.constructCustomerDTO(customer);
        assertNotNull(customerDTO);
    }

}
