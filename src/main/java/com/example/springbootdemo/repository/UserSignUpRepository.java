package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.UserSignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSignUpRepository extends JpaRepository<UserSignUp, Long> {
    UserSignUp findByEmail(String email);
}
