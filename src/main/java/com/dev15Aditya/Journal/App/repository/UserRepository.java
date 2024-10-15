package com.dev15Aditya.Journal.App.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dev15Aditya.Journal.App.entity.User;

public interface UserRepository extends  MongoRepository<User, ObjectId>{

}
