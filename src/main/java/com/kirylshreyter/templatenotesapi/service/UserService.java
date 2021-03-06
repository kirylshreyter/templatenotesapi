package com.kirylshreyter.templatenotesapi.service;

import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.model.VerificationToken;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User updateUserById(Long id, User newUser);

    User updateUserByEmail(String email, User newUser);

    void deleteUserById(Long id);

    boolean existsByEmail(String email);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
