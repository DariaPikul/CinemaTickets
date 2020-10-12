package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    @Override
    public List<Order> getAllByUser(User user) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Order o join fetch o.tickets "
                    + "join fetch o.user WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        }
    }

    @Override
    public List<Order> getAll() {
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> getAllOrdersQuery =
                    criteriaBuilder.createQuery(Order.class);
            Root<Order> root = getAllOrdersQuery.from(Order.class);
            root.fetch("tickets", JoinType.LEFT);
            root.fetch("user", JoinType.INNER);
            getAllOrdersQuery.select(root);
            return session.createQuery(getAllOrdersQuery).getResultList();
        }
    }
}
