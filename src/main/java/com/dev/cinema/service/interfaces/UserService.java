package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.User;
import com.dev.cinema.service.GenericService;
import java.util.Optional;

public interface UserService extends GenericService<User> {
    Optional<User> findByEmail(String email);
}
