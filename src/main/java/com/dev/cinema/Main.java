package com.dev.cinema;

import com.dev.cinema.library.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final String FIRST_TITLE = "Inception";
    private static final String FIRST_DESCRIPTION = "The film is about professional thief "
            + "stealing data by infiltrating the subconscious of his targets.";
    private static final String SECOND_TITLE = "The Da Vinci Code";
    private static final String SECOND_DESCRIPTION = "The mystery thriller novel "
            + "exploring an alternative religious history about Merovingian kings of France, "
            + "the bloodline of Jesus Christ and Mary Magdalene.";

    public static void main(String[] args) {
        printAllMovies();
        createAndPrintMovie(new Movie(FIRST_TITLE, FIRST_DESCRIPTION));
        printAllMovies();
        createAndPrintMovie(new Movie(SECOND_TITLE, SECOND_DESCRIPTION));
        printAllMovies();
    }

    private static void createAndPrintMovie(Movie movie) {
        System.out.println("Creating the movie-object:");
        System.out.println(movie
                + "\nInserting the movie-object data into the database:");
        movie = movieService.create(movie);
        System.out.println(movie);
    }

    private static void printAllMovies() {
        System.out.println("All movies:");
        movieService.getAll().forEach(System.out::println);
    }
}
