package com.example.payment.accounttransfer.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String accountNumber) {
        super ("Account with "+ accountNumber + " does not exist");
    }
}
