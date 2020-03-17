package com.codurance.bank.UI;

import com.codurance.bank.domain.Transaction;
import java.util.List;

public interface Printer {

    void print(List<Transaction> transactions);
}
