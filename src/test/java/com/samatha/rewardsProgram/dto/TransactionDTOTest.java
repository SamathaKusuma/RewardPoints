package com.samatha.rewardsProgram.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionDTOTest {

    @Test
    void calculateRewardMoreThanOnePoint() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(Long.valueOf(10001));
        transactionDTO.setTransactionAmount(Long.valueOf(60));
        assertEquals(10, transactionDTO.getRewardPoints());
    }

    @Test
    void calculateRewardLessThanOnePoint() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(Long.valueOf(10001));
        transactionDTO.setTransactionAmount(Long.valueOf(40));
        assertEquals(0, transactionDTO.getRewardPoints());
    }

    @Test
    void calculateRewardEqualToOnePoint() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(Long.valueOf(10001));
        transactionDTO.setTransactionAmount(Long.valueOf(50));
        assertEquals(0, transactionDTO.getRewardPoints());
    }

    @Test
    void calculateRewardMoreThanTwoPoint() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(Long.valueOf(10001));
        transactionDTO.setTransactionAmount(Long.valueOf(120));
        assertEquals(90, transactionDTO.getRewardPoints());
    }

    @Test
    void calculateRewardLessThanTwoPoint() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(Long.valueOf(10001));
        transactionDTO.setTransactionAmount(Long.valueOf(90));
        assertEquals(40, transactionDTO.getRewardPoints());
    }

    @Test
    void calculateRewardEqualToTwoPoint() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionID(Long.valueOf(10001));
        transactionDTO.setTransactionAmount(Long.valueOf(90));
        assertEquals(40, transactionDTO.getRewardPoints());
    }

}
