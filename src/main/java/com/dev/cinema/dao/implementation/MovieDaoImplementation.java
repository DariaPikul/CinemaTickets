package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.exceptions.DataBaseDataExchangeException;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImplementation implements MovieDao {
    public static final String CREATE_MESSAGE = "Failed to insert the Movie entity";
    public static final String GET_ALL_MESSAGE = "Failed to retrieve all of the Movies entities";

    @Override
    public Movie create(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long id = (Long) session.save(movie);
            transaction.commit();
            movie.setId(id);
            return movie;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataBaseDataExchangeException(CREATE_MESSAGE, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Movie> getAllMoviesQuery = session.createQuery("from Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        } catch (Exception exception) {
            throw new DataBaseDataExchangeException(GET_ALL_MESSAGE, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
