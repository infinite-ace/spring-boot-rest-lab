package com.infinitelambda.userservice.demo.controller;

import com.infinitelambda.userservice.demo.model.UserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Service
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public String createUser(@RequestBody Map<String, String> payload) {
        System.out.println(payload);

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(payload.get("firstName"));
        userDTO.setLastName(payload.get("lastName"));

        String url = "http://localhost:8008/create";
        Map<String, String> addressForUser = new HashMap<>();
        addressForUser.put(payload.get("id"),payload.get("address"));
        this.restTemplate.postForObject(url, addressForUser, String.class);

        return "Creating User.";
    }
}
