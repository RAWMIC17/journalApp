package com.rawmic.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rawmic.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/_journal") //used to add mapping to entire class
public class journalEntryController {
    private Map<ObjectId, JournalEntry> journalEntries =new HashMap<>();    //table to store journals
    
    //@GetMapping("/abc")     //path will be <ip:port>/journal/abc
                              //providing api name is optional
    @GetMapping               //path will be <ip:port>/journal with get request
    public List<JournalEntry> getALL(){     //method inside a controller class has to be public so that it can be accessed by external http requests
        return new ArrayList<>(journalEntries.values());
    }

    // @GetMapping               //we can't have same methods with same path(default), so need different paths like("/abc")
    // public List<JournalEntry> getALL(){     
    //     return new ArrayList<>(journalEntries.values());
    // }

    @PostMapping              //path will be <ip:port>/journal with post request
    public boolean createEntry(@RequestBody JournalEntry myEntry){  //@RequestBody tells spring to take data from the request and turn it into java object for use
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    //To search for specific entry
    @GetMapping("id/{myId}")        //path will be <ip:port>/journal/id/2 with get request
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){   //pathvariable helps to dynamically put variables in path
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")      //GetMapping could also have done the job
    public JournalEntry deleteEntryById(@PathVariable ObjectId myId){
        return journalEntries.remove(myId);     //returns the deleted entry
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        return journalEntries.put(myId,myEntry);    //returns updated entry
    }
}
