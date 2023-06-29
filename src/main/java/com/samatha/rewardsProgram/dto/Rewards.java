package com.samatha.rewardsProgram.dto;


import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rewards {


    private CustomerDTO customerDTO;

    private Map<Month, List<TransactionDTO>> monthlyTransactions = new HashMap<>();

    public Rewards(CustomerDTO customerDTO) {
        super();
        this.customerDTO = customerDTO;
    }

    public Map<Month, Long> getTotalRewardPointsMonthWise() {
        Map<Month, Long> totalMonthlyRewardPoint = new HashMap<>();
        monthlyTransactions.forEach((m, transRecords) -> {
            Long totalPoint = 0l;
            for (TransactionDTO tr : transRecords) {
                totalPoint += tr.getRewardPoints();
            }
            totalMonthlyRewardPoint.put(m, totalPoint);

        });
        return totalMonthlyRewardPoint;
    }


    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Map<Month, List<TransactionDTO>> getMonthlyTransactions() {
        return monthlyTransactions;
    }

    public void setMonthlyTransactions(Map<Month, List<TransactionDTO>> monthlyTransactions) {
        this.monthlyTransactions = monthlyTransactions;
    }
}
