package com.codurance.bank;

public class AccountServiceImplementation implements AccountService {
    private TransactionRepository transactionRepository;
    private Clock clock;

    public AccountServiceImplementation(TransactionRepository transactionRepository, Printer printer, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
    }

    public void deposit(int amount) {
        transactionRepository.save(new Transaction(amount, clock.getCurrentDate()));
    }

    public void withdraw(int amount) {
        throw new UnsupportedOperationException();
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }
}
