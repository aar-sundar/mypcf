package com.example.mongoampq.repository;

import com.example.mongoampq.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User getById(String id);
    User getByEmail(String email);
    User getByMobile(String mobile);
}
