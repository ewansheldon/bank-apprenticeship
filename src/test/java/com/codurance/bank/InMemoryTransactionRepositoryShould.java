package com.codurance.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codurance.bank.domain.Transaction;
import com.codurance.bank.repository.InMemoryTransactionRepository;
import org.junit.jupiter.api.Test;

class InMemoryTransactionRepositoryShould {

    @Test
    public void stores_transaction_to_in_memory() {
        InMemoryTransactionRepository inMemoryTransactionRepository = new InMemoryTransactionRepository();
        inMemoryTransactionRepository.save(new Transaction(1000, "10/12/2020"));

        assertEquals(1, inMemoryTransactionRepository.getAll().size());
        assertEquals(1000, inMemoryTransactionRepository.getAll().get(0).getAmount());
        assertEquals("10/12/2020", inMemoryTransactionRepository.getAll().get(0).getDate());
    }

}