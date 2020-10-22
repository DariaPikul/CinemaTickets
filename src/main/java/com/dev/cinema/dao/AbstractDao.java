package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DatabaseDataExchangeException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    protected final SessionFactory factory;

    @Autowired
    protected AbstractDao(SessionFactory factory) {
        this.factory = factory;
    }

    public T create(T entity) {
        logger.warn("Trying to create the entity - " + entity);
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            logger.info("The entity - " + entity + " was created successfully");
            return entity;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseDataExchangeException("Failed to create the entity - "
                    + entity, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(T entity) {
        logger.warn("Trying to update the entity - " + entity);
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            logger.info("The entity - " + entity + " was updated successfully");
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseDataExchangeException("Failed to update the entity - "
                    + entity, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<T> getAll(Class<T> clazz) {
        try (Session session = factory.openSession()) {
            Query<T> getAllEntitiesQuery =
                    session.createQuery("FROM " + clazz.getName(), clazz);
            return getAllEntitiesQuery.getResultList();
        }
    }
}
