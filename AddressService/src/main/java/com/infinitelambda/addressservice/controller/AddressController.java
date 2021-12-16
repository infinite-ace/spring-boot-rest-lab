package com.infinitelambda.addressservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinitelambda.addressservice.model.Address;
import com.infinitelambda.addressservice.model.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    private Map<String, List<String>> addressBook = new HashMap<>();

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public Address createAddress(@RequestBody HashMap<String,String> payload) {

        String uuid = payload.keySet().stream().findFirst().get();
        String addr = payload.get(payload.keySet().toArray()[0]);

        Address address = new Address();
        List<String> addrList = new ArrayList<>();
        addrList.add(addr);

        address.setAddress(addrList);
        address.setUserUuid(uuid);
        addressService.save(address);

        return address;
    }

    @DeleteMapping("/delete")
    public void deleteAddressesForUser(@RequestBody String uuid) {
        addressBook.remove(uuid);
    }

    @GetMapping("/all")
    public List<Address> getAllAddresses() {
        return addressService.getAll();
    }

    @GetMapping(value = "/find")
    public ResponseEntity<JsonNode> getAddresses(@RequestBody String uuid) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String json = addressService.getAddressesByUserUuid(uuid).toString();
        JsonNode jsonNode = mapper.readTree(json);

        return ResponseEntity.ok(jsonNode);
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
