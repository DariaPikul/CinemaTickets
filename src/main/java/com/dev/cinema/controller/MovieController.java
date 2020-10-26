package com.dev.cinema.controller;

import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.model.entity.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService,
                           MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
        this.movieService = movieService;
    }

    @PostMapping
    public Movie createMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return movieService.create(movieMapper.mapFromRequestDto(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable Long id) {
        return movieMapper.mapToResponseDto(movieService.get(id));
    }
}
