package com.infinitelambda.transactionservice.controller;

import com.infinitelambda.transactionservice.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    Map<String, List<Transaction>> transactionsVault = new HashMap();

    @GetMapping("/all")
    public Map getAllTransactions() {
        return transactionsVault;
    }

    @PostMapping("/register")
    public List<Transaction> registerTransaction(@RequestBody HashMap<String, String> payload) {
        //TODO
        System.out.println(payload);

        String userUUID = payload.get("id");
        Double amount = Double.parseDouble(payload.get("amount"));
        String vendor = payload.get("vendor");
        LocalDate transactionDate = LocalDate.now();
        LocalTime transactionTime = LocalTime.now();

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setVendor(vendor);
        transaction.setDate(transactionDate);
        transaction.setTime(transactionTime);


        if (transactionsVault.get(userUUID) == null) {
            List<Transaction> initialList = new ArrayList<>();
            initialList.add(transaction);
            transactionsVault.put(userUUID, initialList);
        } else {
            transactionsVault.get(userUUID).add(transaction);
        }

        return transactionsVault.get(userUUID);
    }

    @DeleteMapping("/delete")
    public Map delete() {
        transactionsVault.clear();
        // Response msg
        Map<String, String> response = new HashMap<>();
        response.put("message", "All transactions deleted!");
        return response;
    }
}
