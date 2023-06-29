package com.samatha.rewardsProgram.service;

import com.samatha.rewardsProgram.dto.CustomerDTO;
import com.samatha.rewardsProgram.dto.Rewards;
import com.samatha.rewardsProgram.dto.TransactionDTO;
import com.samatha.rewardsProgram.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataBuilder {

    /**
     * @param customer - Takes Customer Object as a parameter
     * @return returns List of CustomerDTO for supplied customer.
     */
    public CustomerDTO constructCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public CustomerDTO constructCustomerDTOWithCustomerName(String customerName) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customerName);
        customerDTO.setCustomerId(100L);
        customerDTO.setCustomerContact("890-97-6789");
        return customerDTO;
    }

    /**
     * @param - Takes List of TransactionDTOs.
     * @return Map of Customer and List of all the transactions by the customer .
     */
    public Map<CustomerDTO, List<TransactionDTO>> getTransactionsPerCustomer(List<TransactionDTO> transactionDTORecords) {
        Map<CustomerDTO, List<TransactionDTO>> customerTransactions = new HashMap<>();
        transactionDTORecords.stream().forEach(transactionRecord -> {
            if (!customerTransactions.containsKey(transactionRecord.getCustomerDTO())) {
                customerTransactions.put(transactionRecord.getCustomerDTO(), new ArrayList<>());
            }
            customerTransactions.get(transactionRecord.getCustomerDTO()).add(transactionRecord);
        });

        return customerTransactions;
    }


    /**
     * @param - Takes CustomerDTO and List of TransactionDTO as parameter.
     * @return All the transactions by the customers in different months.
     */
    public Rewards calculateMonthlyRewards(CustomerDTO customerDTO, List<TransactionDTO> transRecords) {
        Rewards rewards = new Rewards(customerDTO);
        transRecords.stream().forEach(transRecord -> {
            if (!rewards.getMonthlyTransactions().containsKey(transRecord.getTransactionDate().getMonth())) {
                rewards.getMonthlyTransactions().put(transRecord.getTransactionDate().getMonth(),
                        new ArrayList<>());
            }
            rewards.getMonthlyTransactions().get(transRecord.getTransactionDate().getMonth()).add(transRecord);

        });
        return rewards;
    }

}
