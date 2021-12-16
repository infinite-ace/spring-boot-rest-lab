package com.infinitelambda.userservice.model;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private Integer favouriteNumber;
    private String address;

    public static User fromDto(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setFavouriteNumber(userDTO.getFavouriteNumber());

        return user;
    }
}
