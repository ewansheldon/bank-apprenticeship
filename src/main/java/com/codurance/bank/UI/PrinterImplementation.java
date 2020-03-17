package com.codurance.bank.UI;

import com.codurance.bank.domain.Transaction;
import java.util.List;

public class PrinterImplementation implements Printer {

    public PrinterImplementation(Console console) {
    }

    @Override
    public void print(List<Transaction> transactions) {
        throw new UnsupportedOperationException();
    }
}
