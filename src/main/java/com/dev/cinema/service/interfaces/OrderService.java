package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.GenericService;
import java.util.List;

public interface OrderService extends GenericService<Order> {
    /*
    * I've decided to use the shopping cart itself as a method parameter,
    * because there is no point in getting the same shopping cart twice:
    * to get its tickets to pass them as the parameter
    * and then clear the same SC in the service method.
    * */
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrderHistory(User user);
}
