package com.rawmic.journalApp.respositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rawmic.journalApp.entity.JournalEntry;

public interface journalEntryRepository extends MongoRepository<JournalEntry,ObjectId> { //repository interface extending mongo repository interface with parameters as entity type and Id's datatype
            //Don't need to do much here, as all the prebuilt functions are inherited    
} 
