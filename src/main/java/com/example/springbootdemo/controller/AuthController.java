package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.UserSignUp;
import com.example.springbootdemo.service.UserSignUpService;
import com.example.springbootdemo.validator.UserSignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@Component
@RequestMapping("/auth")
public class AuthController {

    private final UserSignUpService userSignUpService;
    private final PasswordEncoder passwordEncoder;
    private final UserSignUpValidator userSignUpValidator;

    @Autowired
    public AuthController(UserSignUpService userSignUpService, PasswordEncoder passwordEncoder, UserSignUpValidator userSignUpValidator) {
        this.userSignUpService = userSignUpService;
        this.passwordEncoder = passwordEncoder;
        this.userSignUpValidator = userSignUpValidator;
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


    @GetMapping("/home")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("userSignUp", new UserSignUp());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String ProcessRegister(@Valid @ModelAttribute("userSignUp") UserSignUp userSignUp,
                                  BindingResult bindingResult){

        userSignUpValidator.validate(userSignUp, bindingResult);

        if(bindingResult.hasErrors())
            return "signup_form";

        else {

            String encodedPassword = passwordEncoder.encode(userSignUp.getPassword());
            userSignUp.setPassword(encodedPassword);

            userSignUpService.saveUserSignUp(userSignUp);

            return "register_success";
        }
    }
}
