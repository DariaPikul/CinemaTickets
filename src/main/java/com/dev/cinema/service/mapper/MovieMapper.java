package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.MovieDto;
import com.dev.cinema.model.entity.Movie;

public class MovieMapper {
    public static MovieDto mapToResponseDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription());
    }

    public static Movie mapFromRequestDto(MovieDto movieDto) {
        return new Movie(movieDto.getId(), movieDto.getTitle(),
                movieDto.getDescription());
    }
}
