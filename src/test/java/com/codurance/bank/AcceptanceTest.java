package com.codurance.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;

public class AcceptanceTest {

    private Console console;
    private TransactionRepository transactionRepository;
    private Printer printer;
    private AccountService accountServiceImplementation;

    @BeforeEach
    void setUp() {
        console = new Console();
        transactionRepository = new InMemoryTransactionRepository();
        printer = new PrinterImplementation(console);
        accountServiceImplementation = new AccountServiceImplementation(transactionRepository, printer);
    }

    @Test
    void it_deposits_withdraws_and_prints_statements() {
        accountServiceImplementation.deposit(1000);
        accountServiceImplementation.deposit(2000);
        accountServiceImplementation.withdraw(500);
        accountServiceImplementation.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("Date || Amount || Balance");
        inOrder.verify(console).print("14/01/2012 || -500 || 2500");
        inOrder.verify(console).print("13/01/2012 || 2000 || 3000");
        inOrder.verify(console).print("10/01/2012 || 1000 || 1000");
    }
}