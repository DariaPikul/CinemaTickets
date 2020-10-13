package com.dev.cinema.dao.interfaces;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getAllByUser(User user);
}
