package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.RegistrationException;
import com.dev.cinema.model.dto.UserDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public void register(@RequestBody UserDto userDto) throws AuthenticationException {
        if (!userDto.getPassword().equals(userDto.getRepeatedPassword())) {
            throw new RegistrationException("The passwords should be equal");
        }
        authenticationService.register(userDto.getEmail(), userDto.getPassword());
    }
}
