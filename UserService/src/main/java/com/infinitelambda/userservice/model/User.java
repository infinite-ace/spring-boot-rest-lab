package com.infinitelambda.userservice.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class User {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

    public User() {
    }
}
