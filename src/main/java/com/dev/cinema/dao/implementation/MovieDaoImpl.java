package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.model.Movie;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    protected MovieDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }
}
