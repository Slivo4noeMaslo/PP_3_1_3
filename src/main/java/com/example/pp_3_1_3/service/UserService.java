package com.example.pp_3_1_3.service;

import com.example.pp_3_1_3.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    User getUserByUsername(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
