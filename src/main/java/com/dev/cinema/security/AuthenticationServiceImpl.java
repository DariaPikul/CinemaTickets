package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.PasswordHashingException;
import com.dev.cinema.library.Inject;
import com.dev.cinema.library.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException,
            PasswordHashingException {
        final String message = "Incorrect email or password.";
        Optional<User> existingUser = userService.findByEmail(email);
        if (existingUser.isEmpty()
                || isPasswordInvalid(password, existingUser.get())) {
            throw new AuthenticationException(message);
        }
        return existingUser.get();
    }

    @Override
    public User register(String email, String password) throws PasswordHashingException {
        User registeringUser = new User();
        registeringUser.setEmail(email);
        registeringUser.setPassword(password);
        return userService.create(registeringUser);
    }

    private boolean isPasswordInvalid(String password, User userFromDB)
            throws PasswordHashingException {
        return !HashUtil.hashPassword(password,
                userFromDB.getSalt()).equals(userFromDB.getPassword());
    }
}
