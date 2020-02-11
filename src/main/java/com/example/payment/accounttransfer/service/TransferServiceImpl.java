package com.example.payment.accounttransfer.service;

import com.example.payment.accounttransfer.entity.Account;
import com.example.payment.accounttransfer.exception.AccountNotFoundException;
import com.example.payment.accounttransfer.exception.InsufficientFundsException;
import com.example.payment.accounttransfer.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Transactional
    public String transferMoney(String sourceAccountNumber, String destinationAccountNumber, Float amount) throws AccountNotFoundException, InsufficientFundsException {

       Optional<Account> sourceAccount = transferRepository.findById(sourceAccountNumber);

       Optional<Account> destinationAccount = transferRepository.findById(destinationAccountNumber);
       if (!sourceAccount.isPresent()) {
           throw new AccountNotFoundException(sourceAccountNumber);
       }

       if (!destinationAccount.isPresent()) {
           throw new AccountNotFoundException(destinationAccountNumber);
       }

       if (sourceAccount.get().getBalance() < amount) {
            throw new InsufficientFundsException();
       }

       sourceAccount.get().setBalance(sourceAccount.get().getBalance() - amount);
       destinationAccount.get().setBalance(destinationAccount.get().getBalance() + amount);
       transferRepository.save(sourceAccount.get());
       transferRepository.save(destinationAccount.get());
        return "Success";
    }
}
