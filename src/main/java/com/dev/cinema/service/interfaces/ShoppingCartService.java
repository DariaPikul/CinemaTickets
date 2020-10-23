package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;

public interface ShoppingCartService extends GenericService<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void addSession(MovieSession movieSession, User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
