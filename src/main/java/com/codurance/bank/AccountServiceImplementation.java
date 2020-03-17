package com.codurance.bank;

public class AccountServiceImplementation implements AccountService {
    private TransactionRepository transactionRepository;
    private Clock clock;

    public AccountServiceImplementation(TransactionRepository transactionRepository, Printer printer, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
    }

    public void deposit(int amount) {
        transactionRepository.save(createTransaction(amount));
    }

    public void withdraw(int amount) {
        transactionRepository.save(createTransaction(-amount));
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }

    private Transaction createTransaction(int amount) {
        return new Transaction(amount, clock.getTime());
    }
}
