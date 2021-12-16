package com.infinitelambda.addressservice.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {
    @Query("{ 'userUuid' : ?0 }")
    List<String> findAddressesByUserUuid(String uuid);
}
