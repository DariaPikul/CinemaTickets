package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DatabaseDataExchangeException;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class AbstractDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    protected final SessionFactory factory;

    protected AbstractDao() {
        this.factory = HibernateUtil.getSessionFactory();
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
            String message = "Failed to create the entity - " + entity;
            logger.error(message, exception);
            throw new DatabaseDataExchangeException(message, exception);
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
            String message = "Failed to update the entity - " + entity;
            logger.error(message, exception);
            throw new DatabaseDataExchangeException(message, exception);
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
