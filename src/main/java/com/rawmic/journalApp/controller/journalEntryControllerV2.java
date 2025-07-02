package com.rawmic.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rawmic.journalApp.entity.JournalEntry;
import com.rawmic.journalApp.service.journalEntryService;

@RestController
@RequestMapping("/journal") 
public class journalEntryControllerV2 {

    @Autowired  //injecting new instance created by spring instead of creating our own with "= new "
    private journalEntryService journal_Entry_Service;      //to use the service in controller

    @GetMapping               
    public ResponseEntity<List<JournalEntry>> getALL(){     
        List<JournalEntry> all= journal_Entry_Service.getAll();
        if(all!=null && !all.isEmpty()){                         //if list is not empty or null
            return new ResponseEntity<>(all,HttpStatus.OK);    
        }
        else{                                                   //else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping              
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());           //to save current date and time
            journal_Entry_Service.saveEntry(myEntry);
            return new ResponseEntity<>( myEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);    //If something wrong happens then exceptions are handled
        }
    }

    
    @GetMapping("id/{myId}")        
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){       //changing type to response entity
        //return journal_Entry_Service.findById(myId).orElse( null);      //dtype is optional so might be present or not
        Optional<JournalEntry> journalEntry=journal_Entry_Service.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);  //response entity can contain both body and http code
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  //if not found
        }
    }

    @DeleteMapping("id/{myId}")      
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){  //? is wildcard pattern, not mandatory to give entity class type
        journal_Entry_Service.deleteByID(myId);   
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry old=journal_Entry_Service.findById(myId).orElse(null); //fetching old entry

        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle():old.getTitle());        //if new title is not null and empty then add new title else use old title
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent():old.getContent());  //if new content is not null and empty then add new content else use old content
            journal_Entry_Service.saveEntry(old);   //saving old
            return new ResponseEntity<>(old,HttpStatus.OK);                             //returning old
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);      //if  entry not found
        }
    }
}
