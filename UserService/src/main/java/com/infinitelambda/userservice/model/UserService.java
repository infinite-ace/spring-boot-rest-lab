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

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserById(String uuid) {
        User user = userRepository.findById(uuid).orElseThrow();
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
