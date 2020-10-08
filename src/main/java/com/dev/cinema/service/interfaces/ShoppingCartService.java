package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.GenericService;

public interface ShoppingCartService extends GenericService<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void addSession(MovieSession movieSession, User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
