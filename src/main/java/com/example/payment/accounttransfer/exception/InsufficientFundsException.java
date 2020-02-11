package com.example.payment.accounttransfer.exception;

public class InsufficientFundsException extends  Exception {

    public InsufficientFundsException() {
        super ("There is insufficient balance to do the transaction");
    }
}
