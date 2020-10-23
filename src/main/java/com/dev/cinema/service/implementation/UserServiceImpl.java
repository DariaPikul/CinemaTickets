package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

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
        return userDao.getAll(User.class);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
