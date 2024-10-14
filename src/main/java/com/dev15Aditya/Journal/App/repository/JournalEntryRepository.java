package com.dev15Aditya.Journal.App.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dev15Aditya.Journal.App.entity.JournalEntry;

public interface JournalEntryRepository extends  MongoRepository<JournalEntry, ObjectId> {

}
