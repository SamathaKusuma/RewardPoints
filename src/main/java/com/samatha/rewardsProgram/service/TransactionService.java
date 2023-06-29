package com.samatha.rewardsProgram.service;

import com.samatha.rewardsProgram.dto.TransactionDTO;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getThreeMonthTransactions();
}
