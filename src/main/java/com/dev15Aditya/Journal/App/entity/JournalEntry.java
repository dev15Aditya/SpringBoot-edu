package com.dev15Aditya.Journal.App.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDate date;

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

    // public LocalDate getDate() {
    //     return date;
    // }

    // public void setDate(LocalDate date) {
    //     this.date = date;
    // }

}
