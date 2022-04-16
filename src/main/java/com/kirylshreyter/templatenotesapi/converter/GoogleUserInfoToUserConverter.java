package com.kirylshreyter.templatenotesapi.converter;

import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.security.model.GoogleUserInfo;
import org.springframework.core.convert.converter.Converter;

public class GoogleUserInfoToUserConverter implements Converter<GoogleUserInfo, User> {
    @Override
    public User convert(GoogleUserInfo source) {
        User user = new User();
        user.setEmail(source.getEmail());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setLocale(source.getLocale());
        user.setPictureUrl(source.getPictureUrl());
        return user;
    }
}
