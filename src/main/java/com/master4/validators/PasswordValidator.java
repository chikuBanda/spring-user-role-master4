package com.master4.validators;

import com.master4.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "valid.confirmedPassword");
        User user = (User) obj;
        if (!user.getPassword().equals(user.getConfirmedPassword())){
            errors.rejectValue("confirmedPassword", "valid.confirmedPasswordDiff");
        }
    }
}
