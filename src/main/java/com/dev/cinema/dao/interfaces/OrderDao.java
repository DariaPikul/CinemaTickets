package com.dev.cinema.dao.interfaces;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.model.entity.Order;
import com.dev.cinema.model.entity.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getAllByUser(User user);
}
