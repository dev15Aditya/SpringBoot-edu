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

import com.dev15Aditya.Journal.App.entity.User;
import com.dev15Aditya.Journal.App.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId userId) {
        Optional<User> user = userService.getUserById(userId);

        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId userId) {
        try{
            userService.deleteUserById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User oldUser = userService.findUserByUserName(userName);

        if(oldUser != null) {
            oldUser.setUserName(!user.getUserName().equals("") ? user.getUserName() : oldUser.getUserName());
            oldUser.setPassword(!user.getPassword().equals("") ? user.getPassword() : oldUser.getPassword());
            // oldUser.setJournalEntries(!user.getJournalEntries().isEmpty() ? user.getJournalEntries() : oldUser.getJournalEntries());
        }

        userService.saveUser(oldUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
