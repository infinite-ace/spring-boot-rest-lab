package com.infinitelambda.userservice.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class UserDTO {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

    public UserDTO() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
