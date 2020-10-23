package com.dev.cinema.model.dto;

public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String showTime;

    public MovieSessionRequestDto() {
    }

    public MovieSessionRequestDto(Long movieId, Long cinemaHallId, String showTime) {
        this.movieId = movieId;
        this.cinemaHallId = cinemaHallId;
        this.showTime = showTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
