package com.example.payment.accounttransfer.controller;

import com.example.payment.accounttransfer.model.Transaction;
import com.example.payment.accounttransfer.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(TransferServiceApi.class)
public class TransferServiceApiTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransferService service;

    @Test
    public void testTransferSuccess() throws Exception {
        Mockito.when(service.transferMoney("abc", "cde", 20.0f)).thenReturn("Success");
        Transaction transaction = new Transaction();
        transaction.setAmount(20.0f);
        transaction.setDestinationAccountNumber("cde");
        transaction.setSourceAccountNumber("abc");
        String content = new ObjectMapper().writeValueAsString(transaction);
        mvc.perform(MockMvcRequestBuilders.post("/transfer").contentType(MediaType.APPLICATION_JSON).content(content).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
