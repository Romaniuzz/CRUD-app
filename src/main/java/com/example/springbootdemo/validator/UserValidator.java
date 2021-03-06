package com.example.springbootdemo.validator;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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

        if(user.getFirstName() == null || user.getFirstName().trim().length()==0){
           return;
        }

        if (user.getLastName() == null || user.getLastName().trim().length()==0){
           return;
        }

        else{


            if (!Character.isUpperCase(user.getFirstName().codePointAt(0)))
                errors.rejectValue("firstName", "", "First name should start with a capital letter");


            if (!Character.isUpperCase(user.getLastName().codePointAt(0)))
                errors.rejectValue("lastName", "", "Last name should start with a capital letter");
            
            if(userService.checkIfUserExistsByEmail(user.getEmail()) != null)
                errors.rejectValue("email", "", "E-mail already exists");
        }

    }

}
