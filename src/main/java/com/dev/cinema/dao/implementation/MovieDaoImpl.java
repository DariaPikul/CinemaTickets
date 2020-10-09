package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Movie;
import java.util.List;

@Dao
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }
}
