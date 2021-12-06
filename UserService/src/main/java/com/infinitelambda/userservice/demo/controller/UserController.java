package com.infinitelambda.userservice.demo.controller;

import com.infinitelambda.userservice.demo.model.UserDTO;
import org.apache.catalina.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Service
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private Map<String, UserDTO> usersMap = new HashMap<>();


    // Crud Methods
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Map createUser(@RequestBody Map<String, String> payload) {

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(payload.get("firstName"));
        userDTO.setLastName(payload.get("lastName"));

        String url = "http://localhost:8008/create";
        Map<String, String> addressForUser = new HashMap<>();
        addressForUser.put(payload.get("id"), payload.get("address"));

        this.restTemplate.postForObject(url, addressForUser, String.class);


        usersMap.put(payload.get("id"), userDTO);
        return usersMap;
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public UserDTO getUser(@RequestBody HashMap<String, String> payload) {
        String id = payload.get("id");
        return usersMap.get(id);
    }

    @DeleteMapping("/delete")
    public Map deleteUser(@RequestBody HashMap<String,String> payload) {
        String uuid = payload.get("id");
        UserDTO deleted = usersMap.remove(uuid);

        String url = "http://localhost:8008/delete";
        this.restTemplate.delete(url, uuid);

        // Response msg
        Map<String, String> response = new HashMap<>();
        response.put("Deleted user is", deleted.toString());
        response.put("message", "Delete successful!");
        return response;
    }

    // Additional endpoints
    @PostMapping(value = "/getAddress", consumes = "application/json", produces = "application/json")
    public String getAddress(@RequestBody String userUUID) {

        String url = "http://localhost:8008/getAddress";
        String address = this.restTemplate.postForObject(url, userUUID, String.class);

        return address;
    }


}
