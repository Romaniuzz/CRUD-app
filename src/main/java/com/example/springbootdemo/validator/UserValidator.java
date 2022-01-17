package com.example.springbootdemo.validator;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.Valid;

@Component
@Valid
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getFirstName().length() < 2 || user.getFirstName().length() > 30) {
            errors.rejectValue("firstName", "", "First name must be between 2 and 30 characters");
        }


        if (user.getLastName().length() < 2 || user.getLastName().length() > 30) {
            errors.rejectValue("lastName", "", "Last name must be between 2 and 30 characters");
        }


        char[] symbolsFirstName = user.getFirstName().toCharArray();

        for (int i = 0; i < symbolsFirstName.length; i++) {
            if (Character.isDigit(symbolsFirstName[i]))
                errors.rejectValue("firstName", "", "First name must have only letters");
        }

        char[] symbolsLastName = user.getLastName().toCharArray();

        for (int i = 0; i < symbolsFirstName.length; i++) {
            if (Character.isDigit(symbolsLastName[i]))
                errors.rejectValue("lastName", "", "Last name must have only letters");
        }


        if (!Character.isUpperCase(user.getFirstName().codePointAt(0)))
            errors.rejectValue("firstName", "", "First name should start with a capital letter");


        if (!Character.isUpperCase(user.getLastName().codePointAt(0)))
            errors.rejectValue("lastName", "", "Last name should start with a capital letter");


    }


}
