package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public MovieSession create(MovieSession movieSession) {
        return movieSessionDao.create(movieSession);
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id, MovieSession.class).get();
    }

    @Override
    public List<MovieSession> getAll() {
        return movieSessionDao.getAll(MovieSession.class);
    }

    @Override
    public void update(MovieSession movieSession) {
        movieSessionDao.update(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
