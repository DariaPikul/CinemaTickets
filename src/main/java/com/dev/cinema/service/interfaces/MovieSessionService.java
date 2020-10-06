package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.GenericService;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService extends GenericService<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
