package com.dev15Aditya.Journal.App.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev15Aditya.Journal.App.entity.JournalEntry;
import com.dev15Aditya.Journal.App.entity.User;
import com.dev15Aditya.Journal.App.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
    @Autowired
    private  JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findUserByUserName(userName);
        journalEntry.setDate(LocalDate.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findUserByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }

    public void updateEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

}
