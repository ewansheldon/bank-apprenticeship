package com.codurance.bank.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codurance.bank.testDoubles.ConsoleSpy;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class PrinterImplementationShould {

    @Test
    public void print_header() {
        ConsoleSpy console = new ConsoleSpy();
        PrinterImplementation printer = new PrinterImplementation(console);

        printer.print(Collections.emptyList());

        assertEquals("Date || Amount || Balance\n", console.verifyPrinted());
    }

}