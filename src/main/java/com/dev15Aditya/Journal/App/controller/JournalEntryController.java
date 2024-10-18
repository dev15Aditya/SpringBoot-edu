package com.dev15Aditya.Journal.App.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev15Aditya.Journal.App.entity.JournalEntry;
import com.dev15Aditya.Journal.App.entity.User;
import com.dev15Aditya.Journal.App.services.JournalEntryService;
import com.dev15Aditya.Journal.App.services.UserService;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getJournalEntryOfUser(@PathVariable String userName) {
        User user = userService.findUserByUserName(userName);
        System.out.println("user: " + user);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try{
            // User user = userService.findUserByUserName(userName);
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);

        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String username) {
        try{
            journalEntryService.deleteById(myId, username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("id/{username}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String username) {
        JournalEntry oldEntry = journalEntryService.getById(id).orElse(null);

        if(oldEntry != null) {
            oldEntry.setTitle((!newEntry.getTitle().equals("")) ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent((newEntry.getContent() != null && !newEntry.getContent().equals("")) ? newEntry.getContent() : oldEntry.getContent());
        }
        
        journalEntryService.updateEntry(oldEntry);
        return new ResponseEntity<>(oldEntry, HttpStatus.OK);
    }
}
