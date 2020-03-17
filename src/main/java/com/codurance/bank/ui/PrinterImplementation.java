package com.codurance.bank.ui;

import com.codurance.bank.domain.Transaction;
import java.util.List;

public class PrinterImplementation implements Printer {

    private Console console;

    public PrinterImplementation(Console console) {
        this.console = console;
    }

    @Override
    public void print(List<Transaction> all) {
        console.print("Date || Amount || Balance\n");
    }
}
