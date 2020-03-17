package com.codurance.bank;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplementationShould {
    private final Calendar DATE = new GregorianCalendar(2013, JANUARY, 31);

    @Test
    void create_transaction_and_save_when_deposit() {
        TransactionRepositorySpy transactionRepository = new TransactionRepositorySpy();
        Printer printer = new PrinterDummy();
        Clock clock = new ClockMock();
        AccountService account = new AccountServiceImplementation(transactionRepository, printer, clock);

        int amount = 1000;
        account.deposit(amount);

        assertEquals(amount, transactionRepository.verifyAmount());
        assertEquals(DATE, transactionRepository.verifyDate());
    }

    private class PrinterDummy implements Printer {
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
    }

    private class ClockMock implements Clock {
        public Calendar getTime() {
            return DATE;
        }
    }
}