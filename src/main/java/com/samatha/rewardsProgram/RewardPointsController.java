package com.samatha.rewardsProgram;


import com.samatha.rewardsProgram.dto.Rewards;
import com.samatha.rewardsProgram.model.Customer;
import com.samatha.rewardsProgram.model.Transactions;
import com.samatha.rewardsProgram.repository.CustomerRepository;
import com.samatha.rewardsProgram.repository.TransactionsRepository;
import com.samatha.rewardsProgram.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rewardsProgram/api/v1")
public class RewardPointsController {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    CustomerService customerService;

    @GetMapping("/getCustomers")
    public ResponseEntity<List<Customer>> getCustomer(Customer customer) {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<List<Transactions>> getTransactions(Transactions transactions) {
        return ResponseEntity.ok(transactionsRepository.findAll());
    }

    @GetMapping("/getRewards")
    public ResponseEntity<List<Rewards>> getRewards() {
        return ResponseEntity.ok(customerService.getRewards());
    }

    @GetMapping("/getRewards/customer/{customerID}")
    public ResponseEntity<Rewards> getRewardsForCustomer(@PathVariable(value = "customerID") Long customerRepository) {
        return ResponseEntity.ok(customerService.getRewardsForCustomer(customerRepository));
    }

}
