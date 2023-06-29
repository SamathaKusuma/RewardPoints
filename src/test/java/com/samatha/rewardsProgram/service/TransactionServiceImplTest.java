package com.samatha.rewardsProgram.service;

import com.samatha.rewardsProgram.dto.TransactionDTO;
import com.samatha.rewardsProgram.model.Transactions;
import com.samatha.rewardsProgram.repository.TransactionsRepository;
import com.samatha.rewardsProgram.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionServiceImplTest {

    @Mock
    TransactionsRepository transactionsRepository;

    @Mock
    DataBuilder dataBuilder;

    private TransactionDTO transactionDTO = new TransactionDTO();

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl();

    @Test
    void getThreeMonthTransactions() {
        Transactions transactions = new Transactions();
        transactions.setTransactionID(Long.valueOf(100));
        transactions.setTransactionAmount(Long.valueOf(200));
        List<Transactions> transactionsList = new ArrayList<>();
        transactionsList.add(transactions);
        doReturn(transactionsList).when(transactionsRepository).findAllTransactionsInLastThreeMonths();
        List<TransactionDTO> threeMonthsRecordList = transactionService.getThreeMonthTransactions();
        assertEquals(1, threeMonthsRecordList.size());
    }

}
