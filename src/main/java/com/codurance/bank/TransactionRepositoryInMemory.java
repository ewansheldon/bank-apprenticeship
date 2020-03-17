package com.codurance.bank;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryInMemory implements TransactionRepository {
    private List<Transaction> transactions;

    public TransactionRepositoryInMemory() {
        transactions = new ArrayList<>();
    }

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }
}
