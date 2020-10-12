package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.library.Inject;
import com.dev.cinema.library.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        User user = shoppingCart.getUser();
        List<Ticket> tickets = shoppingCart.getTickets();
        Order order = new Order(user, tickets, LocalDateTime.now());
        shoppingCartService.clear(shoppingCart);
        return create(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getAllByUser(user);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
