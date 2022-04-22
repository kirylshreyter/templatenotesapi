package com.kirylshreyter.templatenotesapi.converter;

import com.kirylshreyter.templatenotesapi.model.SignUpDetails;
import com.kirylshreyter.templatenotesapi.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SignUpDetailsToUserConverter implements Converter<SignUpDetails, User> {
    @Override
    public User convert(SignUpDetails source) {
        User user = new User();
        user.setEmail(source.getEmail());
        user.setName(source.getName());
        user.setEncodedPassword(new BCryptPasswordEncoder().encode(source.getPassword()));
        user.setActive(true);
        user.setExpired(false);
        user.setCredentialsExpired(false);
        user.setLocked(false);
        return user;
    }
}
