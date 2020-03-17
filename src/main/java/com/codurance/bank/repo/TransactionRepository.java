package com.codurance.bank.repo;

import com.codurance.bank.domain.Transaction;
import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

  List<Transaction> getAll();
}
