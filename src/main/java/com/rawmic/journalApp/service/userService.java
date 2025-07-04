package com.rawmic.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rawmic.journalApp.entity.JournalEntry;
import com.rawmic.journalApp.entity.users;
import com.rawmic.journalApp.respositry.userRepository;

@Component
public class userService {
    @Autowired
    private userRepository user_repository;

    public void saveEntry(users user){           //function to save entry
        user_repository.save(user);
    } 

    public List<users> getAll(){
        return user_repository.findAll();
    }

    public Optional<users> findById(ObjectId id){
        return user_repository.findById(id);
    }

    public void deleteByID(ObjectId id){
        user_repository.deleteById(id);
    }

    public users findByUsername(String username){
        return user_repository.findByUsername(username);        //declared this method in repository
    }
}
