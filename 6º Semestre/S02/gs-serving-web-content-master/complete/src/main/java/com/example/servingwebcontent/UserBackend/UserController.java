package com.example.servingwebcontent.UserBackend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/registers")
    public RedirectView newUser(@RequestParam (name = "LoginRegister") String login, @RequestParam (name = "PasswordRegister") String password) {
        if(repository.findByUsername(login) == null){
            repository.save(new User(login, new BCryptPasswordEncoder().encode(password)));
            return new RedirectView("/login");
        }

        return null;


    }
}