package com.codurance.bank.ui;

import static java.util.Calendar.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codurance.bank.domain.Transaction;
import com.codurance.bank.testDoubles.ConsoleSpy;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

class PrinterImplementationShould {

    @Test
    public void print_header() {
        ConsoleSpy console = new ConsoleSpy();
        PrinterImplementation printer = new PrinterImplementation(console);

        printer.print(Collections.emptyList());

        assertEquals("Date || Amount || Balance\n", console.verifyPrinted());
    }

    @Test
    public void print_transactions() {
        Transaction transaction = new Transaction(1000, new GregorianCalendar(2012, JANUARY, 14));
        ConsoleSpy console = new ConsoleSpy();
        PrinterImplementation printer = new PrinterImplementation(console);

        printer.print(Collections.singletonList(transaction));

        assertEquals("Date || Amount || Balance\n" +
            "14/01/2012 || 1000 || 1000", console.verifyPrinted());
    }

    @Test
    public void print_transactions_with_balance() {
        ConsoleSpy console = new ConsoleSpy();
        PrinterImplementation printer = new PrinterImplementation(console);

        printer.print(Arrays.asList(
            new Transaction(1000, new GregorianCalendar(2012, JANUARY, 14)),
            new Transaction(-2000, new GregorianCalendar(2013, JANUARY, 14))));

        assertEquals("Date || Amount || Balance\n" +
            "14/01/2013 || -2000 || -1000\n" +
            "14/01/2012 || 1000 || 1000", console.verifyPrinted());
    }

}