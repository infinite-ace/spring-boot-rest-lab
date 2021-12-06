package com.infinitelambda.userservice.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddressController {

    @PostMapping("/create")
    public String createAddress(@RequestBody String payload) {

        System.out.println("Address is: " + payload);
        return "Creating Address";
    }

}
