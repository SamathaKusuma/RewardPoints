package com.samatha.rewardsProgram.service;

import com.samatha.rewardsProgram.dto.Rewards;

import java.util.List;

public interface CustomerService {

    List<Rewards> getRewards();

    Rewards getRewardsForCustomer(Long customerID);
}
