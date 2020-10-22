package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.MovieSessionDto;
import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionMapper(CinemaHallService cinemaHallService,
                              MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public static MovieSessionDto mapToResponseDto(MovieSession movieSession) {
        return new MovieSessionDto(movieSession.getId(),
                movieSession.getMovie().getId(),
                movieSession.getCinemaHall().getId(),
                movieSession.getShowTime().format(DATE_TIME_FORMATTER));
    }

    public MovieSession mapFromRequestDto(MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setId(movieSessionDto.getId());
        movieSession.setMovie(movieService.get(movieSessionDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime()));
        return movieSession;
    }
}
