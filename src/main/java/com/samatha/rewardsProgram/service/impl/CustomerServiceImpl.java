package com.samatha.rewardsProgram.service.impl;

import com.samatha.rewardsProgram.dto.CustomerDTO;
import com.samatha.rewardsProgram.dto.Rewards;
import com.samatha.rewardsProgram.dto.TransactionDTO;
import com.samatha.rewardsProgram.model.Customer;
import com.samatha.rewardsProgram.repository.CustomerRepository;
import com.samatha.rewardsProgram.service.CustomerService;
import com.samatha.rewardsProgram.service.DataBuilder;
import com.samatha.rewardsProgram.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    TransactionService transactionService;

    @Autowired
    DataBuilder dataBuilder;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * @return monthly rewards and total monthly rewards for all the customers
     */
    @Override
    public List<Rewards> getRewards() {
        List<Rewards> rewardsList = new ArrayList<>();
        List<TransactionDTO> threeMonthTransactions = transactionService.getThreeMonthTransactions();
        if (!threeMonthTransactions.isEmpty()) {
            Map<CustomerDTO, List<TransactionDTO>> customerTransactions = dataBuilder.getTransactionsPerCustomer(threeMonthTransactions);
            customerTransactions.forEach((customerDTO, transactionRecord) -> {
                Rewards rewards = dataBuilder.calculateMonthlyRewards(customerDTO, transactionRecord);
                rewardsList.add(rewards);
            });
        }
        return rewardsList;
    }

    /**
     * @param customerId - Takes customerID as a parameter
     * @return returns monthly rewards and total monthly rewards of a particular customer.
     */
    @Override
    public Rewards getRewardsForCustomer(Long customerId) {
        Customer customer = getCustomer(customerId);
        CustomerDTO customerDTO = dataBuilder.constructCustomerDTO(customer);
        Rewards rewards = new Rewards(customerDTO);
        List<TransactionDTO> threeMonthTransactions = transactionService.getThreeMonthTransactions();
        if (!threeMonthTransactions.isEmpty()) {
            Map<CustomerDTO, List<TransactionDTO>> customerTransactions = dataBuilder.getTransactionsPerCustomer(threeMonthTransactions);
            if (customerTransactions.containsKey(customerDTO)) {
                rewards = dataBuilder.calculateMonthlyRewards(customerDTO, customerTransactions.get(customerDTO));
            }
        }
        return rewards;

    }

    /**
     * @param customerId - Takes customerId as a parmeter
     * @return Customer Object if exists, of the supplied customerID.
     */
    public Customer getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent())
            return customer.get();
        return new Customer();
    }


}
