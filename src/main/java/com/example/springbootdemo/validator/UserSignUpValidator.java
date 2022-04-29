package com.example.springbootdemo.validator;

import com.example.springbootdemo.model.UserSignUp;
import com.example.springbootdemo.service.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;

@Component
@Valid
public class UserSignUpValidator implements Validator {

    private final UserSignUpService userSignUpService;


    @Autowired
    public UserSignUpValidator(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserSignUp.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSignUp userSignUp = (UserSignUp) target;

        if(userSignUp.getFirstName() == null || userSignUp.getFirstName().trim().length() == 0){
            return;
        }


        if(userSignUp.getLastName() == null || userSignUp.getLastName().trim().length() == 0){
            return;
        }

        if(userSignUp.getEmail() == null || userSignUp.getEmail().trim().length() == 0){
            return;
        }

        if(userSignUp.getPassword() == null || userSignUp.getPassword().trim().length() == 0){
            return;
        }

        else{

            boolean checkIsNotLetterInFirstName = isNotLetter(userSignUp.getFirstName());
            boolean checkIsNotLetterInLastName = isNotLetter(userSignUp.getLastName());

            if(checkIsNotLetterInFirstName){
                errors.rejectValue("firstName", "", "First name should contain only letters");
            }

            if(checkIsNotLetterInLastName){
                errors.rejectValue("lastName", "", "Last name should contain only letters");
            }


            if (!Character.isUpperCase(userSignUp.getFirstName().codePointAt(0)))
                errors.rejectValue("firstName", "", "First name should start with a capital letter");


            if (!Character.isUpperCase(userSignUp.getLastName().codePointAt(0)))
                errors.rejectValue("lastName", "", "Last name should start with a capital letter");

            if(userSignUpService.findUserSignUpByEmail(userSignUp.getEmail()) != null){
                errors.rejectValue("email", "", "E-mail already exists");
            }
        }
    }


    public static boolean isNotLetter(String s) {
        if(s == null)
            return false;

        for(char c: s.toCharArray()) {
            if(!Character.isLetter(c))
                return true;
        }
        return false;
    }

}
