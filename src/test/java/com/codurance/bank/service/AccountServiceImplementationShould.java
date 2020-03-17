package com.codurance.bank.service;

import com.codurance.bank.domain.Transaction;
import com.codurance.bank.repo.TransactionRepository;
import com.codurance.bank.service.AccountService;
import com.codurance.bank.service.AccountServiceImplementation;
import com.codurance.bank.ui.Printer;
import com.codurance.bank.utils.Clock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplementationShould {

    private final Calendar DATE = new GregorianCalendar(2013, JANUARY, 31);
    private AccountService account;
    private Clock clock;
    private Printer printer;
    private TransactionRepositorySpy transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositorySpy();
        printer = new PrinterDummy();
        clock = new ClockMock();
        account = new AccountServiceImplementation(transactionRepository, printer, clock);
    }

    @Test
    void create_transaction_and_save_when_deposit() {
        int amount = 1000;
        account.deposit(amount);

        assertEquals(amount, transactionRepository.verifyAmount());
        assertEquals(DATE, transactionRepository.verifyDate());
    }

    @Test
    void create_transaction_and_save_when_withdraw() {
        int amount = 1000;
        account.withdraw(amount);

        assertEquals(-amount, transactionRepository.verifyAmount());
        assertEquals(DATE, transactionRepository.verifyDate());
    }

    @Test
    void pass_all_transactions_for_printing() {
        RepositoryStub transactionRepository = new RepositoryStub();
        PrinterSpy printerSpy = new PrinterSpy();
        account = new AccountServiceImplementation(transactionRepository, printerSpy, clock);

        account.printStatement();

        assertEquals(1, printerSpy.verifyPrinted());
    }


    private class PrinterSpy extends PrinterDummy {

        private int transactionsPrinted;

        public int verifyPrinted() {
            return transactionsPrinted;
        }

        public void print(List<Transaction> all) {
            transactionsPrinted++;
        }
    }

    private class PrinterDummy implements Printer {

        public void print(List<Transaction> all) {
        }
    }

    private class TransactionRepositorySpy implements TransactionRepository {

        private int amount;

        private Calendar date;

        public int verifyAmount() {
            return amount;
        }

        public Calendar verifyDate() {
            return date;
        }

        public void save(Transaction transaction) {
            amount = transaction.getAmount();
            date = transaction.getTime();
        }

        public List<Transaction> getAll() {
            return null;
        }

    }

    private class ClockMock implements Clock {

        public Calendar getTime() {
            return DATE;
        }

    }

    private class RepositoryStub implements TransactionRepository {

        public void save(Transaction transaction) {

        }

        public List<Transaction> getAll() {
            return null;
        }
    }
}