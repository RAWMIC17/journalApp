package com.rawmic.journalApp.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document(collection="journal_entries")       //used to tell spring that below things are the mongodb collection mapped entity
                //each instance of journal entry will be a document(row)
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// @EqualsAndHashCode
// @Builder

@Data //is equivalent to all of the above annotations
public class JournalEntry { //POJO- plain old java object
    @Id         //unique key in collection, optional
    private ObjectId id;

    private String title;
    private String content;
    private LocalDateTime date;

    // public LocalDateTime getDate() {
    //     return date;
    // }
    // public void setDate(LocalDateTime date) {
    //     this.date = date;
    // }
    // public ObjectId getId() {
    //     return id;
    // }
    // public void setId(ObjectId id) {
    //     this.id = id;
    // }

    // public String getTitle() {
    //     return title;
    // }
    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // public String getContent() {
    //     return content;
    // }
    // public void setContent(String content) {
    //     this.content = content;
    // }
}
