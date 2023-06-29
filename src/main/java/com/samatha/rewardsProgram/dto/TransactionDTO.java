package com.samatha.rewardsProgram.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;


public class TransactionDTO {

    private static final Long ONE_POINT_VALUE = 50l;
    private static final Long TWO_POINTS_VALUE = 100L;

    private Long transactionID;

    private LocalDateTime transactionDate;

    private Long transactionAmount;


    @JsonIgnore
    private CustomerDTO customerDTO;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TransactionDTO o = (TransactionDTO) obj;
        if (transactionID == null) {
            if (o.transactionID != null)
                return false;
        } else if (!transactionID.equals(o.transactionID))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((transactionID == null) ? 0 : transactionID.hashCode());
        return result;
    }

    public long getRewardPoints() {
        long rewardsPoints = 0L;
        if (transactionAmount > TWO_POINTS_VALUE) {
            rewardsPoints = ONE_POINT_VALUE + (transactionAmount - TWO_POINTS_VALUE) * 2;
        } else {
            if (transactionAmount > ONE_POINT_VALUE) {
                rewardsPoints = transactionAmount - ONE_POINT_VALUE;
            }
        }
        return rewardsPoints;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

}
