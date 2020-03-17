package com.codurance.bank.ui;

import com.codurance.bank.domain.Transaction;
import java.text.SimpleDateFormat;
import java.util.List;

public class PrinterImplementation implements Printer {

    private Console console;

    public PrinterImplementation(Console console) {
        this.console = console;
    }

    @Override
    public void print(List<Transaction> all) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");

        console.print("Date || Amount || Balance\n");

        for (Transaction transaction : all) {
            console.print(formatter.format(transaction.getCalendar().getTime()) + " || " +
                transaction.getAmount() + " || " + transaction.getAmount());
        }
    }
}
