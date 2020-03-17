package com.codurance.bank.service;

import com.codurance.bank.utils.Clock;
import com.codurance.bank.UI.Printer;
import com.codurance.bank.domain.Transaction;
import com.codurance.bank.repository.TransactionRepository;

public class AccountServiceImplementation implements AccountService {
    private TransactionRepository transactionRepository;
    private Printer printer;
    private Clock clock;

    public AccountServiceImplementation(TransactionRepository transactionRepository, Printer printer, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.printer = printer;
        this.clock = clock;
    }

    public void deposit(int amount) {
        transactionRepository.save(createTransaction(amount));
    }

    public void withdraw(int amount) {
        transactionRepository.save(createTransaction(-amount));
    }

    public void printStatement() {
        printer.print(transactionRepository.getAll());
    }

    private Transaction createTransaction(int amount) {
        return new Transaction(amount, clock.getCurrentDate());
    }
}
