package com.infinitelambda.userservice.demo.controller;

import com.infinitelambda.userservice.demo.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    Map<String, Transaction> transactionsVault = new HashMap();

    @GetMapping("/all")
    public Map getAllTransactions() {
        return transactionsVault;
    }

    @PostMapping("/register")
    public void registerTransaction(@RequestBody HashMap<String, String> payload) {
        //TODO
    }
}
