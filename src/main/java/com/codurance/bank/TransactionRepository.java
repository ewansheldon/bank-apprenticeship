package com.codurance.bank;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> getAll();
}
