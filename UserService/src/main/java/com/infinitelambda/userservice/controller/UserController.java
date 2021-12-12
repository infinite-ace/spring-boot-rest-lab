package com.infinitelambda.userservice.controller;

import com.infinitelambda.userservice.model.UserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
    public Map createUser(@RequestBody Map<String, String> payload) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(payload.get("firstName"));
        userDTO.setLastName(payload.get("lastName"));

        String url = "http://localhost:8008/create";
        Map<String, String> addressForUser = new HashMap<>();
        addressForUser.put(payload.get("id"), payload.get("address"));

        try {
            this.restTemplate.postForObject(url, addressForUser, String.class);
        } catch (Exception e) {
            String errorMessage = "Address service is not running.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        usersMap.put(payload.get("id"), userDTO);
        return usersMap;
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public UserDTO getUser(@RequestBody HashMap<String, String> payload) {
        String id = payload.get("id");
        return usersMap.get(id);
    }

    @PutMapping(value = "/modify", consumes = "application/json", produces = "application/json")
    public UserDTO modify(@RequestBody HashMap<String, String> payload) {
        String id = payload.get("id");

        UserDTO modifiedUser = usersMap.get(id);
        modifiedUser.setFirstName(payload.get("firstname"));
        modifiedUser.setLastName(payload.get("lastname"));

        usersMap.remove(usersMap.get(id));
        usersMap.put(id, modifiedUser);


        return modifiedUser;
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

    @GetMapping("/all")
    public Map getAll() {
        return usersMap;
    }

    @GetMapping("/test")
    public Map test() {
        Map<String, LocalDateTime> response = new HashMap<>();
//        response.put("Deleted user is", deleted.toString());
        response.put("date", LocalDateTime.now());
        return response;
    }

    @PostMapping(value = "/getAddress", consumes = "application/json", produces = "application/json")
    public String getAddress(@RequestBody String userUUID) {

        String url = "http://localhost:8008/getAddress";
        String address = this.restTemplate.postForObject(url, userUUID, String.class);

        return address;
    }

    @PostMapping(value = "/transaction", consumes = "application/json", produces = "application/json")
    public Map transaction(@RequestBody HashMap<String, String> transaction) {

        String url = "http://localhost:8024/register";

        try {
            this.restTemplate.postForObject(url, transaction, List.class);
        } catch (Exception e) {
            String errorMessage = "Transaction service is not running.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // Response msg
        Map<String, String> response = new HashMap<>();
        response.put("message", "Transaction executed and saved successfully!");
        return response;
    }
}
