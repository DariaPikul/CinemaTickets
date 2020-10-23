package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.User;
import java.util.Optional;

public interface UserService extends GenericService<User> {
    Optional<User> findByEmail(String email);
}
