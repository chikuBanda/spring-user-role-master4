package com.master4.validators;

import com.master4.entities.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordClass implements ConstraintValidator<Password,User> {
    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getConfirmedPassword());
    }
}
