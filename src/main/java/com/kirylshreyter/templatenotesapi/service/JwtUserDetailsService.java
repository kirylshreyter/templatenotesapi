package com.kirylshreyter.templatenotesapi.service;

import com.kirylshreyter.templatenotesapi.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        com.kirylshreyter.templatenotesapi.model.User user = userService.getUserByEmail(email);
        return new User(
                email,
                user.getEncodedPassword(),
                user.getEnabled(),
                !user.getExpired(),
                !user.getCredentialsExpired(),
                !user.getLocked(),
                new ArrayList<>()
        );
    }
}
