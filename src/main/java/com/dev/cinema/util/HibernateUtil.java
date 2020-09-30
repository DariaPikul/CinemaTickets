package com.dev.cinema.util;

import com.dev.cinema.exceptions.SessionFactoryCreationErrorException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();
    private static final String MESSAGE = "Failed to create the session factory";

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception exception) {
            throw new SessionFactoryCreationErrorException(MESSAGE, exception);
        }
    }
}
