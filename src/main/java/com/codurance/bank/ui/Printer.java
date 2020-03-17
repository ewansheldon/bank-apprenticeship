package com.codurance.bank.ui;

import com.codurance.bank.domain.Transaction;
import java.util.List;

public interface Printer {

    void print(List<Transaction> all);
}
