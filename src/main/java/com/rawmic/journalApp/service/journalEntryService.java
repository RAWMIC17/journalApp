package com.rawmic.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rawmic.journalApp.entity.JournalEntry;
import com.rawmic.journalApp.respositry.journalEntryRepository;

@Component
public class journalEntryService {

    @Autowired      //used for automatic dependency injection
    private journalEntryRepository journal_entry_repository;    //spring creates an implementation of journalEntryRepository interface in runtime despite it not being a bean

    public void saveEntry(JournalEntry journalEntry){           //function to save entry
        journal_entry_repository.save(journalEntry);        
    }

    public List<JournalEntry> getAll(){                     //function get all entries
        return journal_entry_repository.findAll();         
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journal_entry_repository.findById(id);
    }

    public void deleteByID(ObjectId id){
        journal_entry_repository.deleteById(id);
    }
}
