package com.kirylshreyter.templatenotesapi.validator;

import com.kirylshreyter.templatenotesapi.annotation.UserEmailUnique;
import com.kirylshreyter.templatenotesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserEmailUniqueValidator implements ConstraintValidator<UserEmailUnique, String> {
    @Override
    public void initialize(UserEmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
