package com.example.springbootdemo.security;

import com.example.springbootdemo.model.UserSignIn;
import com.example.springbootdemo.repository.UserSignInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("UserSignInDetailsServiceImpl")
public class UserSignInDetailsServiceImpl implements UserDetailsService {

    private final UserSignInRepository userSignInRepository;

    @Autowired
    public UserSignInDetailsServiceImpl(UserSignInRepository userSignInRepository) {
        this.userSignInRepository = userSignInRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSignIn userSignIn = userSignInRepository.findByEmail(email).orElseThrow(() ->
               new UsernameNotFoundException("User doesn`t exists"));

        return SecurityUserSignIn.fromUserSignIn(userSignIn);
    }
}
