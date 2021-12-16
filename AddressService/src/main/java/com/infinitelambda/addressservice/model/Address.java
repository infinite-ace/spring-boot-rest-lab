package com.infinitelambda.addressservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Data
@Document
public class Address {
    @Id
    String userUuid;
    List<String> address;

    public Address() {
    }
}
