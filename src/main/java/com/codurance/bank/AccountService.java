package com.codurance.bank;

public interface AccountService {
    void deposit(int amount);

    void withdraw(int amount);

    void printStatement();
}
