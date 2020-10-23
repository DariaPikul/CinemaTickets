package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.model.entity.Order;
import com.dev.cinema.model.entity.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    protected OrderDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Order> getAllByUser(User user) {
        try (Session session = factory.openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "JOIN FETCH o.tickets WHERE o.user = :user", Order.class)
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
            getAllOrdersQuery.distinct(true);
            getAllOrdersQuery.select(root);
            return session.createQuery(getAllOrdersQuery).getResultList();
        }
    }
}
