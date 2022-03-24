package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.UserSignIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSignInRepository extends JpaRepository<UserSignIn, Long> {
   Optional<UserSignIn> findByEmail(String email);
}
