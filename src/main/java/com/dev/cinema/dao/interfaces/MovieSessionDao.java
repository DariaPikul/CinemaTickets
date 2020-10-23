package com.dev.cinema.dao.interfaces;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.model.entity.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao extends GenericDao<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
