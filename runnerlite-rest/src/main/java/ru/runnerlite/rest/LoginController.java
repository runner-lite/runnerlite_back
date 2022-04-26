package ru.runnerlite.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class LoginController {

    @GetMapping("/login")
    public User login(Authentication auth) {
        return (User) auth.getPrincipal();
    }

}
