package com.dev.cinema.controller;

import com.dev.cinema.model.dto.MovieSessionDto;
import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.service.interfaces.MovieSessionService;
import com.dev.cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie_sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping("/")
    public MovieSession addMovieSession(
            @RequestBody MovieSessionDto movieSessionRequestDto) {
        return movieSessionService.create(movieSessionMapper
                .mapFromRequestDto(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionDto> getAvailableMovieSessions(
            @RequestParam Long movieId, @RequestParam LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(MovieSessionMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
}
