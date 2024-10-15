package com.dev15Aditya.Journal.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev15Aditya.Journal.App.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev15Aditya.Journal.App.entity.User;


@RestController
@RequestMapping("/journal")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("path")
    public User<?> getAllUsers() {
        List<User> users = userService.getAll();
        return new User<>(users)
    }
    
    
}
