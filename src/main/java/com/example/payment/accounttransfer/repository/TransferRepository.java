package com.example.payment.accounttransfer.repository;


import com.example.payment.accounttransfer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Account, String> {

}
