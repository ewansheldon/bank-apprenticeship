package com.codurance.bank;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private List<Transaction> transactions;

    public InMemoryTransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }
}
