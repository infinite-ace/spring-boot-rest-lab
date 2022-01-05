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
    private String vendor;
    private Double amount;
    private LocalDate date;
    private LocalTime time;

    public Transaction() {
    }
}
