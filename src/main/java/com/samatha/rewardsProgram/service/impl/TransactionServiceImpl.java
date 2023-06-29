package com.samatha.rewardsProgram.service.impl;

import com.samatha.rewardsProgram.dto.TransactionDTO;
import com.samatha.rewardsProgram.model.Transactions;
import com.samatha.rewardsProgram.repository.TransactionsRepository;
import com.samatha.rewardsProgram.service.DataBuilder;
import com.samatha.rewardsProgram.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    DataBuilder dataBuilder;

    /**
     * @return returns List of TransactionDTO for the last three month of transactions.
     */
    @Override
    public List<TransactionDTO> getThreeMonthTransactions() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        List<Transactions> threeMonthsRecord = transactionsRepository.findAllTransactionsInLastThreeMonths();
        threeMonthsRecord.stream().forEach(transactions -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            BeanUtils.copyProperties(transactions, transactionDTO);
            transactionDTO.setCustomerDTO(dataBuilder.constructCustomerDTO(transactions.getCustomer()));
            transactionDTOList.add(transactionDTO);
        });

        return transactionDTOList;
    }
}
