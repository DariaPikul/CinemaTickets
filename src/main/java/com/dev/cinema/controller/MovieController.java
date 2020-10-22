package com.dev.cinema.controller;

import com.dev.cinema.model.dto.MovieDto;
import com.dev.cinema.model.entity.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie createMovie(@RequestBody MovieDto movieRequestDto) {
        return movieService.create(MovieMapper.mapFromRequestDto(movieRequestDto));
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(MovieMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
}
