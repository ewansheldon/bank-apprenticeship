package com.codurance.bank.testDoubles;

import com.codurance.bank.ui.Console;

public class ConsoleSpy implements Console {

    private StringBuilder str;

    public String verifyPrinted() {
        return str.toString();
    }

    @Override
    public void print(String line) {
        str = new StringBuilder(line);
    }
}
