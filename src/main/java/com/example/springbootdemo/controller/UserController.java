package com.example.springbootdemo.controller;


import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Component
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }


    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    @PreAuthorize("hasAuthority('users:write')")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    @PreAuthorize("hasAuthority('users:write')")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        userValidator.validate(user,bindingResult);

        if(bindingResult.hasErrors())
            return "user-create";

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors())
        return "user-update";

        userService.saveUser(user);
        return "redirect:/users";
    }
}