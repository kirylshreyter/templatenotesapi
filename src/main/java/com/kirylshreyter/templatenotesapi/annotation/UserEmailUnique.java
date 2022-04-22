package com.kirylshreyter.templatenotesapi.annotation;

import com.kirylshreyter.templatenotesapi.validator.UserEmailUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserEmailUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserEmailUnique {
    String message() default "The field is not unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
