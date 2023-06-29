package com.samatha.rewardsProgram.repository;

import com.samatha.rewardsProgram.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    @Query(value = "SELECT trans FROM Transactions trans where trans.transactionDate >= DATEADD(MONTH, -3, CURRENT_DATE) ")
    List<Transactions> findAllTransactionsInLastThreeMonths();
}
