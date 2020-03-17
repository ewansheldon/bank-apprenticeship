package com.codurance.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplementationShould {

    private TransactionRepositorySpy transactionRepository;
    private Printer printer;
    private AccountService accountServiceImplementation;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositorySpy();
        printer = new PrinterDummy();
        ClockMock clock = new ClockMock();
        accountServiceImplementation = new AccountServiceImplementation(transactionRepository, printer, clock);
    }

    @Test
    void create_transaction_and_send_to_repository() {
        int amount = 1000;
        accountServiceImplementation.deposit(amount);

        assertEquals(1000, transactionRepository.getLastAmount());
        assertEquals("10/10/2012", transactionRepository.getLastDate());
    }

    private class TransactionRepositorySpy implements TransactionRepository {

        private int lastAmount;
        private String lastDate;

        public int getLastAmount() {
            return lastAmount;
        }

        public String getLastDate() {
            return lastDate;
        }

        public void save(Transaction transaction) {
            lastAmount = transaction.getAmount();
            lastDate = transaction.getDate();
        }
    }

    private class PrinterDummy implements Printer {
    }

    private class ClockMock implements Clock {
        public String getCurrentDate() {
            return "10/10/2012";
        }
    }
}