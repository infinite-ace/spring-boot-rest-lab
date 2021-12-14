package com.infinitelambda.userservice.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'favouriteNumber' : ?0 }")
    List<User> findUsersByFavouriteNumber(Integer favouriteNumber);
}
