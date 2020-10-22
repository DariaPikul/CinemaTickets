package com.dev.cinema.dao.interfaces;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;
import java.util.Optional;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    Optional<ShoppingCart> getByUser(User user);
}
