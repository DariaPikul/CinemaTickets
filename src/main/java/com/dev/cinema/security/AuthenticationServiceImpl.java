package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> existingUser = userService.findByEmail(email);
        if (existingUser.isEmpty()
                || isPasswordInvalid(password, existingUser.get())) {
            throw new AuthenticationException("Incorrect email or password.");
        }
        return existingUser.get();
    }

    @Override
    public User register(String email, String password) {
        User registeringUser = new User();
        registeringUser.setEmail(email);
        registeringUser.setPassword(password);
        registeringUser = userService.create(registeringUser);
        shoppingCartService.registerNewShoppingCart(registeringUser);
        return registeringUser;
    }

    private boolean isPasswordInvalid(String password, User userFromDB) {
        return !HashUtil.hashPassword(password,
                userFromDB.getSalt()).equals(userFromDB.getPassword());
    }
}
