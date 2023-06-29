package com.samatha.rewardsProgram.dto;

import com.samatha.rewardsProgram.service.DataBuilder;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RewardsTest {


    DataBuilder dataBuilder = new DataBuilder();

    CustomerDTO customer = dataBuilder.constructCustomerDTOWithCustomerName("test");

    Rewards rewards = new Rewards(customer);

    @Test
    void getTotalRewardPointsMonthWiseTest() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setCustomerDTO(customer);
        transactionDTO.setTransactionDate(LocalDateTime.of(2023,
                Month.MAY, 1, 19, 30, 40));
        transactionDTO.setTransactionAmount(Long.valueOf(90));
        transactionDTO.setTransactionID(1000l);
        transactionDTOList.add(transactionDTO);
        rewards.getMonthlyTransactions().put(Month.JUNE, transactionDTOList);
        assertTrue(rewards.getTotalRewardPointsMonthWise().get(Month.JUNE).equals(Long.valueOf(40)));
    }


}
