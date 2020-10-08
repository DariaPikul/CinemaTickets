package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Movie;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    @Override
    public List<Movie> getAll() {
        try (Session session = factory.openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("FROM Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        }
    }
}
