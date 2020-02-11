package com.example.payment.accounttransfer.service;

import com.example.payment.accounttransfer.exception.AccountNotFoundException;
import com.example.payment.accounttransfer.exception.InsufficientFundsException;

public interface TransferService {
    public String transferMoney(String sourceAccountNumber, String destinationAccountNumber, Float amount) throws AccountNotFoundException, InsufficientFundsException;
}
