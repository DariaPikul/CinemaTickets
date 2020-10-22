package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public void update(Movie movie) {
        movieDao.update(movie);
    }
}
