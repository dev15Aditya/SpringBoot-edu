package com.dev15Aditya.Journal.App.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev15Aditya.Journal.App.entity.User;
import com.dev15Aditya.Journal.App.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(User user) {
        user.setDate(LocalDate.now());
        userRepository.save(user);
        return true;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
