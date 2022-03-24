package com.example.springbootdemo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "authenticated_users")
public class UserSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authenticated_user_email", nullable = false, unique = true, length = 255)
    @NotBlank(message = "Email is required for filling")
    @Email
    private String email;

    @Column(name = "authenticated_user_password", nullable = false, length = 255)
    @NotBlank(message = "Password is required for filling")
    private String password;

    @Column(name = "authenticated_user_name", nullable = false)
    @NotBlank(message = "First name is required for filling")
    private String FirstName;


    @Column(name = "authenticated_user_surname", nullable = false)
    @NotBlank(message = "First name is required for filling")
    private String LastName;

}
