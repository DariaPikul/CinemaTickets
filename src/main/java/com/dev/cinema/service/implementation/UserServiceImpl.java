package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.library.Inject;
import com.dev.cinema.library.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt));
        return userDao.create(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
