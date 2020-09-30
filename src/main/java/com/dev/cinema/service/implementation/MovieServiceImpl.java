package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.library.Inject;
import com.dev.cinema.library.Service;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
