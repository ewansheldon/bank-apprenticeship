package com.codurance.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codurance.bank.UI.Printer;
import com.codurance.bank.utils.Clock;
import com.codurance.bank.domain.Transaction;
import com.codurance.bank.repository.TransactionRepository;
import com.codurance.bank.service.AccountService;
import com.codurance.bank.service.AccountServiceImplementation;
import java.util.ArrayList;
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

    @Test
    void print_statement_sends_transactions_to_printer() {
        PrinterSpy printer = new PrinterSpy();

        accountServiceImplementation.printStatement();

        ArrayList<Transaction> transactions = new ArrayList<>();
        assertEquals(transactions, printer.verifyPrinted());
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

        public void print(List<Transaction> transactions) {
        }
    }

    private class ClockMock implements Clock {

        public String getCurrentDate() {
            return DATE;
        }
    }

    private class PrinterSpy implements Printer {

        private List<Transaction> transactionsPrinted = new ArrayList<>();

        @Override
        public void print(List<Transaction> transactions) {
            transactionsPrinted = transactions;
        }

        public List<Transaction> verifyPrinted() {
            return transactionsPrinted;
        }
    }
}