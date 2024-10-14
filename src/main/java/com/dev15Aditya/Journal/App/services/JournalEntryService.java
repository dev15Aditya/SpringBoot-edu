package com.dev15Aditya.Journal.App.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev15Aditya.Journal.App.entity.JournalEntry;
import com.dev15Aditya.Journal.App.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
    @Autowired
    private  JournalEntryRepository journalEntryRepository;

    public boolean  saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDate.now());
        journalEntryRepository.save(journalEntry);
        return true;
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

    public void updateEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

}
