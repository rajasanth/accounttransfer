package com.example.payment.accounttransfer.service;

import com.example.payment.accounttransfer.entity.Account;
import com.example.payment.accounttransfer.exception.AccountNotFoundException;
import com.example.payment.accounttransfer.exception.InsufficientFundsException;
import com.example.payment.accounttransfer.repository.TransferRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class TransferServiceTests {

    @Autowired
    private TransferService transferService;

    @MockBean
    private TransferRepository transferRepository;

    @Test
    public void testTransferSuccess() throws Exception {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(250.9f);
        sourceAccount.setAccountNumber("abc");

        Account destinationAccount = new Account();
        destinationAccount.setBalance(250.9f);
        destinationAccount.setAccountNumber("abc");

        Mockito.when(transferRepository.findById("abc"))
                .thenReturn(Optional.of(sourceAccount));
        Mockito.when(transferRepository.findById("cde"))
                .thenReturn(Optional.of(destinationAccount));
        String a = transferService.transferMoney("abc", "cde", 200.8f);
        Assert.assertEquals("Success", a);
    }

    @Test
    public void testTransferInsufficientFunds() throws Exception {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(50.0f);
        sourceAccount.setAccountNumber("abc");

        Account destinationAccount = new Account();
        destinationAccount.setBalance(200.0f);
        destinationAccount.setAccountNumber("abc");

        Mockito.when(transferRepository.findById("abc"))
                .thenReturn(Optional.of(sourceAccount));
        Mockito.when(transferRepository.findById("cde"))
                .thenReturn(Optional.of(destinationAccount));
        String errorMessage = "";
        try {
            transferService.transferMoney("abc", "cde", 200.8f);
        }
        catch (InsufficientFundsException e) {
            errorMessage = e.getMessage();
        }

        Assert.assertEquals("There is insufficient balance to do the transaction", errorMessage);
    }

    @Test
    public void testTransferAccountNotFoundException() throws Exception {


        Mockito.when(transferRepository.findById("abc"))
                .thenReturn(Optional.empty());
        Mockito.when(transferRepository.findById("cde"))
                .thenReturn(Optional.empty());
        String errorMessage = "";
        try {
            transferService.transferMoney("abc", "cde", 200.8f);
        }
        catch (AccountNotFoundException e) {
            errorMessage = e.getMessage();
        }

        Assert.assertEquals("Account with abc does not exist", errorMessage);
    }
}
