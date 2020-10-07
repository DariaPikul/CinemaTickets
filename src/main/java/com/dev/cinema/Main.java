package com.dev.cinema;

import com.dev.cinema.library.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final String FIRST_MOVIE_TITLE = "Inception";
    private static final String FIRST_MOVIE_DESCRIPTION = "The film is about professional thief "
            + "stealing data by infiltrating the subconscious of his targets.";
    private static final String SECOND_MOVIE_TITLE = "The Da Vinci Code";
    private static final String SECOND_MOVIE_DESCRIPTION = "The mystery thriller novel "
            + "exploring an alternative religious history about Merovingian kings of France, "
            + "the bloodline of Jesus Christ and Mary Magdalene.";
    private static final String FIRST_HALL_DESCRIPTION = "Bigger hall";
    private static final String SECOND_HALL_DESCRIPTION = "VIP hall";
    private static final int FIRST_HALL_CAPACITY = 250;
    private static final int SECOND_HALL_CAPACITY = 20;
    private static final LocalDateTime FIRST_SHOW_TIME =
            LocalDateTime.parse("03-10-2020 11:30:00",
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private static final LocalDateTime SECOND_SHOW_TIME =
            LocalDateTime.now();

    public static void main(String[] args) {
        printAllMovies();
        Movie firstMovie = new Movie(FIRST_MOVIE_TITLE, FIRST_MOVIE_DESCRIPTION);
        createAndPrintMovie(firstMovie);
        printAllMovies();
        Movie secondMovie = new Movie(SECOND_MOVIE_TITLE, SECOND_MOVIE_DESCRIPTION);
        createAndPrintMovie(secondMovie);
        printAllMovies();

        printAllCinemaHalls();
        CinemaHall firstCinemaHall = new CinemaHall(FIRST_HALL_CAPACITY, FIRST_HALL_DESCRIPTION);
        createAndPrintCinemaHall(firstCinemaHall);
        printAllCinemaHalls();
        CinemaHall secondCinemaHall = new CinemaHall(SECOND_HALL_CAPACITY, SECOND_HALL_DESCRIPTION);
        createAndPrintCinemaHall(secondCinemaHall);
        printAllCinemaHalls();

        printAllMovieSessions();
        MovieSession firstMovieSession =
                new MovieSession(firstMovie, firstCinemaHall, FIRST_SHOW_TIME);
        createAndPrintMovieSession(firstMovieSession);
        printAllMovieSessions();
        MovieSession secondMovieSession =
                new MovieSession(secondMovie, secondCinemaHall, SECOND_SHOW_TIME);
        createAndPrintMovieSession(secondMovieSession);
        printAllMovieSessions();
        findAndPrintAvailableMovieSessions(secondMovie.getId(), LocalDate.now());
    }

    private static void createAndPrintMovie(Movie movie) {
        System.out.println("\nCreating the movie-object:");
        System.out.println(movie
                + "\nInserting the movie-object data into the database:");
        movie = movieService.create(movie);
        System.out.println(movie);
    }

    private static void printAllMovies() {
        System.out.println("\nAll movies:");
        movieService.getAll().forEach(System.out::println);
    }

    private static void createAndPrintCinemaHall(CinemaHall cinemaHall) {
        System.out.println("\nCreating the cinema hall-object:");
        System.out.println(cinemaHall
                + "\nInserting the cinema hall-object data into the database:");
        cinemaHall = cinemaHallService.create(cinemaHall);
        System.out.println(cinemaHall);
    }

    private static void printAllCinemaHalls() {
        System.out.println("\nAll cinema halls:");
        cinemaHallService.getAll().forEach(System.out::println);
    }

    private static void createAndPrintMovieSession(MovieSession movieSession) {
        System.out.println("\nCreating the movie session-object:");
        System.out.println(movieSession
                + "\nInserting the movie session-object data into the database:");
        movieSession = movieSessionService.create(movieSession);
        System.out.println(movieSession);
    }

    private static void findAndPrintAvailableMovieSessions(Long movieId, LocalDate date) {
        System.out.println("\nSearching fot the available movie sessions:");
        System.out.println(movieSessionService.findAvailableSessions(movieId, date));
    }

    private static void printAllMovieSessions() {
        System.out.println("\nAll movie sessions:");
        movieSessionService.getAll().forEach(System.out::println);
    }
}
