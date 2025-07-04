package com.rawmic.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "users")
@Data               //for using lombok to autogenrate codes
public class users {
    
    @Id     //this will map id with mongodb generated object id
    private ObjectId id;        //we can even take it as string and spring data mongodb will convert it to objectid
    
    @Indexed(unique =true)      //indexing to make search faster and username should be unique and need to set property in application.properties
    @NonNull                    //username should not be null
    private String username;

    @NonNull                    //Password should not be null
    private String password;

    @DBRef                      //used to create reference from users to their personal entries
    private List<JournalEntry> journal_entries=new ArrayList<>();           //just embedding ids' of entries not whole documents
}
