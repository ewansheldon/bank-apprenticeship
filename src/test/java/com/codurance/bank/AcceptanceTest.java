package com.codurance.bank;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;

public class AcceptanceTest {
    @Test
    void it_deposits_withdraws_and_prints_statements() {
        Console console = new Console();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        Printer printer = new PrinterImplementation(console);
        AccountService accountServiceImplementation = new AccountServiceImplementation(transactionRepository, printer);

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