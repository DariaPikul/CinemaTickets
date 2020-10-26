package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.model.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieResponseDto mapToResponseDto(Movie movie) {
        return new MovieResponseDto(movie.getId(), movie.getTitle(), movie.getDescription());
    }

    public Movie mapFromRequestDto(MovieRequestDto movieRequestDto) {
        return new Movie(movieRequestDto.getTitle(),
                movieRequestDto.getDescription());
    }
}
