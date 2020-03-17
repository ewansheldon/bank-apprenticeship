package com.codurance.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountServiceImplementationShould {

    private TransactionRepositorySpy transactionRepository;
    private Printer printer;
    private AccountService accountServiceImplementation;
    private String DATE;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositorySpy();
        printer = new PrinterDummy();
        ClockMock clock = new ClockMock();
        accountServiceImplementation = new AccountServiceImplementation(transactionRepository,
            printer, clock);
    }

    @Test
    void create_transaction_and_send_to_repository_on_deposit() {
        DATE = "10/10/2012";
        int amount = 1000;
        accountServiceImplementation.deposit(amount);

        assertEquals(amount, transactionRepository.getAmount());
        assertEquals(DATE, transactionRepository.getDate());
    }

    @Test
    void create_transaction_and_send_to_repository_on_withdraw() {
        DATE = "11/10/2012";
        int amount = 1000;
        accountServiceImplementation.withdraw(amount);

        assertEquals(-amount, transactionRepository.getAmount());
        assertEquals(DATE, transactionRepository.getDate());
    }

    private class TransactionRepositorySpy implements TransactionRepository {

        private int amount;
        private String date;

        public int getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }

        public void save(Transaction transaction) {
            amount = transaction.getAmount();
            date = transaction.getDate();
        }

        public List<Transaction> getAll() {
            return null;
        }
    }

    private class PrinterDummy implements Printer {

    }

    private class ClockMock implements Clock {

        public String getCurrentDate() {
            return DATE;
        }
    }
}