package com.example.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 letters")
    @NotBlank(message = "First name is required for filling")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "First name should contain only latin letters")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 letters")
    @NotBlank(message = "Last name is required for filling")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name should contain only latin letters")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "email is required for filling")
    @Email(message = "Please enter a valid e-mail address")
    private String email;
}