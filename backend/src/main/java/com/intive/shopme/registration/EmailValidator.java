package com.intive.shopme.registration;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {

    private final UserService userService;

    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type == String.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        var email = (String) target;
        if (userService.existsByEmail(email)) {
            errors.rejectValue("email","","Email address " + email + " is already used!");
        }
    }
}
