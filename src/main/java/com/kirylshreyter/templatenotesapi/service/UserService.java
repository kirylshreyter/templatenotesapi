package com.kirylshreyter.templatenotesapi.service;

import com.kirylshreyter.templatenotesapi.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);

    User updateUserById(Long id, User newUser);

    User updateUserByEmail(String email, User newUser);

    void deleteUserById(Long id);
}
