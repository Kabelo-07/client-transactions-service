package com.moeketsi.lefa.assessment.clienttransactions.repository;

import com.moeketsi.lefa.assessment.clienttransactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Set<Transaction> findTransactionByClient(Integer clientId);
}
