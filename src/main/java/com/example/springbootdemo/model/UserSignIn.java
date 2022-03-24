package com.example.springbootdemo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "authenticated_users")
public class UserSignIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authenticated_user_email")
    @NotBlank(message = "Username is required field")
    private String email;

    @Column(name = "authenticated_user_password")
    @NotBlank(message = "Password is required field")
    private String password;

    @Column(name = "authenticated_user_name")
    private String name;


    @Column(name = "authenticated_user_surname")
    private String surname;

    @Column(name = "authenticated_user_role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
