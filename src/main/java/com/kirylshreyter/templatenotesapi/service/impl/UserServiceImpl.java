package com.kirylshreyter.templatenotesapi.service.impl;

import com.kirylshreyter.templatenotesapi.exception.UserNotFoundException;
import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.model.VerificationToken;
import com.kirylshreyter.templatenotesapi.repository.UserRepository;
import com.kirylshreyter.templatenotesapi.repository.VerificationTokenRepository;
import com.kirylshreyter.templatenotesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User updateUserById(Long id, User newUser) {
        return repository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setEnabled(newUser.getEnabled());
                    return repository.save(user);
                })
                .orElseGet(() -> repository.save(newUser));
    }

    @Override
    public User updateUserByEmail(String email, User newUser) {
        return repository.findByEmail(email)
                .map(user -> {
                    newUser.setId(user.getId());
                    return repository.save(newUser);
                })
                .orElseGet(() -> repository.save(newUser));
    }

    @Override
    public void deleteUserById(Long id) {
        if (!repository.existsById(id)) throw new UserNotFoundException(id);
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }
}
