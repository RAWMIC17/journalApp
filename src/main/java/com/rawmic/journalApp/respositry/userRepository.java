package com.rawmic.journalApp.respositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rawmic.journalApp.entity.users;

public interface userRepository extends MongoRepository<users, ObjectId>{
    users findByUsername(String username);
    
}