package com.infinitelambda.userservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findUsersByFavNum(Integer favNum) {
        List<User> users = userRepository.findUsersByFavouriteNumber(favNum);

        return users;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
