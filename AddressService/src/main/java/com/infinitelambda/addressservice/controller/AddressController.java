package com.infinitelambda.addressservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    private Map<String, List<String>> addressBook = new HashMap<>();

    @PostMapping("/create")
    public void createAddress(@RequestBody HashMap<String,String> payload) {

        String uuid = payload.keySet().stream().findFirst().get();
        String address = payload.get(payload.keySet().toArray()[0]);

        if (addressBook.get(uuid) == null) {
            // List is empty, then we add a new one with our address
            List<String> addressList = new ArrayList<>();
            addressList.add(address);
            addressBook.put(uuid, addressList);
        }
    }

    @DeleteMapping("/delete")
    public void deleteAddressesForUser(@RequestBody String uuid) {
        addressBook.remove(uuid);
    }

    @GetMapping("/all")
    public Map<String, List<String>> getAllAddresses() {
        return addressBook;
    }

    @PostMapping(value = "/getAddress", consumes = "application/json", produces = "application/json")
    public List<String> getAddress(@RequestBody HashMap<String,String> payload) {

        String uuid = payload.get("id");
        // Adding the first or an additional address to the List for specific user
        addressBook.get(uuid).add(uuid);

        return addressBook.get(uuid);
    }

    // Additional endpoints
    @PostMapping("/addAddress")
    public Map addAddress(@RequestBody HashMap<String, String> payload) {
        String uuid = payload.get("id");
        String address = payload.get("address");

        addressBook.get(uuid).add(address);

        // Response msg
        Map<String, String> response = new HashMap<>();
        response.put("message", "Address added successfuly!");
        return response;
    }



}
