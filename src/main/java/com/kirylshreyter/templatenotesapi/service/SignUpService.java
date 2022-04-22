package com.kirylshreyter.templatenotesapi.service;

import com.kirylshreyter.templatenotesapi.model.SignUpDetails;
import com.kirylshreyter.templatenotesapi.model.User;

public interface SignUpService {
    User createAccount(SignUpDetails details);
}
