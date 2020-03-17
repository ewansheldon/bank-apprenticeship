package com.codurance.bank.repo;

import com.codurance.bank.domain.Transaction;
import com.codurance.bank.repo.TransactionRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static java.util.Calendar.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionRepositoryInMemoryShould {
  @Test
  void save_transactions() {
    Transaction transaction = new Transaction(1000, new GregorianCalendar(2013, JANUARY, 31));
    TransactionRepositoryInMemory transactionRepositoryInMemory = new TransactionRepositoryInMemory();
    transactionRepositoryInMemory.save(transaction);
    assertEquals(1, transactionRepositoryInMemory.getAll().size());
    assertEquals(transaction, transactionRepositoryInMemory.getAll().get(0));
  }
}
