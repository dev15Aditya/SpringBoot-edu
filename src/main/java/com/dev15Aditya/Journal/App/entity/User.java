package com.dev15Aditya.Journal.App.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private  String userName;

    @NonNull
    private String password;

    private LocalDate date;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
