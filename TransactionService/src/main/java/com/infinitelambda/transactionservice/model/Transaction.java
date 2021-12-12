package com.infinitelambda.transactionservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Transaction {

    @Id
    private Long id;
    String vendor;
    Double amount;
    LocalDate date;
    LocalTime time;

    public Transaction() {
    }
}
