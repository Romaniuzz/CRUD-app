package com.example.springbootdemo.service;

import com.example.springbootdemo.model.UserSignUp;
import com.example.springbootdemo.repository.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {

    private final UserSignUpRepository userSignUpRepository;


    @Autowired
    public UserSignUpService(UserSignUpRepository userSignUpRepository) {
        this.userSignUpRepository = userSignUpRepository;
    }


    public UserSignUp saveUserSignUp(UserSignUp userSignUp){
        return userSignUpRepository.save(userSignUp);
    }

    public UserSignUp findUserSignUpByEmail(String email){
        return userSignUpRepository.findByEmail(email);
    }
}
