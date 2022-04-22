package com.kirylshreyter.templatenotesapi.service.impl;

import com.kirylshreyter.templatenotesapi.exception.UserNotFoundException;
import com.kirylshreyter.templatenotesapi.model.SignUpDetails;
import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.repository.UserRepository;
import com.kirylshreyter.templatenotesapi.service.SignUpService;
import com.kirylshreyter.templatenotesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public User createAccount(SignUpDetails details) {
        return userService.saveUser(conversionService.convert(details, User.class));
    }
}
