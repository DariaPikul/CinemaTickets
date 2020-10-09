package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.User;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

@Dao
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        }
    }

    @Override
    public List<User> getAll() {
        return super.getAll(User.class);
    }
}
