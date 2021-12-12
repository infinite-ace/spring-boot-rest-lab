package com.infinitelambda.addressservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class Address {
    @Id
    private Long id;
    String userUuid;
    String address;

    public Address() {
    }
}
