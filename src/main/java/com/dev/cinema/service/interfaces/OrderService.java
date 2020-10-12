package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.GenericService;
import java.util.List;

public interface OrderService extends GenericService<Order> {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrderHistory(User user);
}
