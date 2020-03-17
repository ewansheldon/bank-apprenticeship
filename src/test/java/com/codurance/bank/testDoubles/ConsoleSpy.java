package com.codurance.bank.testDoubles;

import com.codurance.bank.ui.Console;

public class ConsoleSpy implements Console {

    private StringBuilder str;

    public ConsoleSpy() {
        str = new StringBuilder();
    }

    public String verifyPrinted() {
        return str.toString();
    }

    @Override
    public void print(String line) {
        str.append(line);
    }
}
