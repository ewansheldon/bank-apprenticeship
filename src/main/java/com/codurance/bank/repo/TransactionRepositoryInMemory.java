package com.codurance.bank.repo;

import com.codurance.bank.domain.Transaction;
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

    public List<Transaction> getAll() {
        return transactions;
    }
}
