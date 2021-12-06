package com.infinitelambda.userservice.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AddressController {

    private Map<String, String> addressBook = new HashMap<>();

    @PostMapping("/create")
    public void createAddress(@RequestBody HashMap<String,String> payload) {

        addressBook.putAll(payload);
        System.out.println(addressBook);
    }

}
