package com.infinitelambda.addressservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<String> getAddressesByUserUuid(String uuid) {
        return addressRepository.findAddressesByUserUuid(uuid);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public void delete(String uuid) {
//
//        Address adr = new Address();
//        adr.setUserUuid(uuid);
//        adr.setAddresses(addressRepository.findAddressesByUserUuid(uuid));
//
//        addressRepository.delete(adr);

    }
}
