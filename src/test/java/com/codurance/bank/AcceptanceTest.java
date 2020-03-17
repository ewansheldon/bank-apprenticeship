package com.codurance.bank;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {
    @Test
    void deposits_withdraws_and_prints_statement() {
        TransactionRepository transactionRepository = new TransactionRepositoryInMemory();
        ConsoleSpy console = new ConsoleSpy();
        Printer printer = new PrinterImplementation(console);
        Clock clock = new ClockDummy();
        AccountService account = new AccountServiceImplementation(transactionRepository, printer, clock);

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

        assertEquals("Date || Amount || Balance\n" +
                "14/01/2012 || -500 || 2500\n" +
                "13/01/2012 || 2000 || 3000\n" +
                "10/01/2012 || 1000 || 1000", console.verifyPrinted());
    }

    private class ConsoleSpy implements Console {

        public String verifyPrinted() {
            return null;
        }
    }

    private class ClockDummy implements Clock {
        public Calendar getTime() {
            return null;
        }
    }
}
