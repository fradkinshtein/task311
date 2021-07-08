package com.example.task311.service;


import com.example.task311.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteById(long id);
    User findById(long id);
    List<User> findAllUsers();

}