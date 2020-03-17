package com.codurance.bank.domain;

import java.util.Calendar;

public class Transaction {
    private final int amount;
    private final Calendar time;

    public Transaction(int amount, Calendar time) {
        this.amount = amount;
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public Calendar getTime() {
        return time;
    }
}
