package com.example.payment.accounttransfer.controller;

import com.example.payment.accounttransfer.exception.AccountNotFoundException;
import com.example.payment.accounttransfer.exception.InsufficientFundsException;
import com.example.payment.accounttransfer.model.Transaction;
import com.example.payment.accounttransfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferServiceApi {

    @Autowired
    TransferService transferService;

    @PostMapping(path = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String transferMoney(@RequestBody Transaction transaction) throws AccountNotFoundException, InsufficientFundsException {
        return transferService.transferMoney(transaction.getSourceAccountNumber(), transaction.getDestinationAccountNumber(), transaction.getAmount());
    }

}
