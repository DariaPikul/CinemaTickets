package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.entity.CinemaHall;
import com.dev.cinema.model.entity.Movie;
import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.model.entity.Order;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.interfaces.MovieSessionService;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final MovieService movieService = context.getBean(MovieService.class);
    private static final CinemaHallService cinemaHallService =
            context.getBean(CinemaHallService.class);
    private static final MovieSessionService movieSessionService =
            context.getBean(MovieSessionService.class);
    private static final UserService userService = context.getBean(UserService.class);
    private static final AuthenticationService authenticationService =
            context.getBean(AuthenticationService.class);
    private static final ShoppingCartService shoppingCartService =
            context.getBean(ShoppingCartService.class);
    private static final OrderService orderService = context.getBean(OrderService.class);
    private static final String FIRST_EMAIL = "alice@mail.com";
    private static final String FIRST_PASSWORD = "AliceLovesBob";
    private static final String SECOND_EMAIL = "bob@mail.com";
    private static final String SECOND_PASSWORD = "BobLovesMoney";
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
    private static final LocalDateTime THIRD_SHOW_TIME =
            LocalDateTime.parse("03-12-2020 11:30:00",
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        /*
         * Movies
         * */
        printAllMovies();
        Movie firstMovie = new Movie(FIRST_MOVIE_TITLE, FIRST_MOVIE_DESCRIPTION);
        createAndPrintMovie(firstMovie);
        printAllMovies();
        Movie secondMovie = new Movie(SECOND_MOVIE_TITLE, SECOND_MOVIE_DESCRIPTION);
        createAndPrintMovie(secondMovie);
        printAllMovies();
        /*
         * Cinema Halls
         * */
        printAllCinemaHalls();
        CinemaHall firstCinemaHall = new CinemaHall(FIRST_HALL_CAPACITY, FIRST_HALL_DESCRIPTION);
        createAndPrintCinemaHall(firstCinemaHall);
        printAllCinemaHalls();
        CinemaHall secondCinemaHall = new CinemaHall(SECOND_HALL_CAPACITY, SECOND_HALL_DESCRIPTION);
        createAndPrintCinemaHall(secondCinemaHall);
        printAllCinemaHalls();
        /*
         * Movie Sessions
         * */
        printAllMovieSessions();
        MovieSession firstMovieSession =
                new MovieSession(firstMovie, firstCinemaHall, FIRST_SHOW_TIME);
        createAndPrintMovieSession(firstMovieSession);
        printAllMovieSessions();
        MovieSession secondMovieSession =
                new MovieSession(secondMovie, secondCinemaHall, SECOND_SHOW_TIME);
        createAndPrintMovieSession(secondMovieSession);
        MovieSession thirdMovieSession =
                new MovieSession(secondMovie, secondCinemaHall, THIRD_SHOW_TIME);
        createAndPrintMovieSession(thirdMovieSession);
        printAllMovieSessions();
        findAndPrintAvailableMovieSessions(secondMovie.getId(), LocalDate.now());
        /*
         * Users
         * */
        printAllUsers();
        registerAndPrintUser(FIRST_EMAIL, FIRST_PASSWORD);
        printAllUsers();
        registerAndPrintUser(SECOND_EMAIL, SECOND_PASSWORD);
        registerAndPrintUser(SECOND_EMAIL, SECOND_PASSWORD);
        printAllUsers();
        loginAndPrintUser(FIRST_EMAIL, FIRST_PASSWORD);
        loginAndPrintUser(SECOND_EMAIL, SECOND_PASSWORD);
        findAndPrintUserByEmail(FIRST_EMAIL);
        findAndPrintUserByEmail(SECOND_EMAIL);
        User userAlice = userService.findByEmail(FIRST_EMAIL).get();
        User userBob = userService.findByEmail(SECOND_EMAIL).get();
        /*
         * Shopping Cart
         * */
        printAllShoppingCarts();
        addSessionToShoppingCart(firstMovieSession, userAlice);
        addSessionToShoppingCart(secondMovieSession, userBob);
        printAllShoppingCarts();
        getAndPrintShoppingCartByUser(userAlice);
        getAndPrintShoppingCartByUser(userBob);
        clearShoppingCart(shoppingCartService.getByUser(userAlice));
        printAllShoppingCarts();
        /*
         * Orders
         * */
        printAllOrders();
        completeOrder(shoppingCartService.getByUser(userBob));
        printOrderHistoryByUser(userBob);
        addSessionToShoppingCart(firstMovieSession, userBob);
        addSessionToShoppingCart(thirdMovieSession, userBob);
        completeOrder(shoppingCartService.getByUser(userBob));
        printOrderHistoryByUser(userBob);
        addSessionToShoppingCart(firstMovieSession, userAlice);
        completeOrder(shoppingCartService.getByUser(userAlice));
        printOrderHistoryByUser(userAlice);
        printAllOrders();
    }

    private static void createAndPrintMovie(Movie movie) {
        movieService.create(movie);
    }

    private static void printAllMovies() {
        logger.debug("All movies:");
        movieService.getAll().forEach(logger::debug);
    }

    private static void createAndPrintCinemaHall(CinemaHall cinemaHall) {
        cinemaHallService.create(cinemaHall);
    }

    private static void printAllCinemaHalls() {
        logger.debug("All cinema halls:");
        cinemaHallService.getAll().forEach(logger::debug);
    }

    private static void createAndPrintMovieSession(MovieSession movieSession) {
        movieSessionService.create(movieSession);
    }

    private static void findAndPrintAvailableMovieSessions(Long movieId, LocalDate date) {
        logger.debug("Searching for the available movie sessions:");
        logger.debug(movieSessionService.findAvailableSessions(movieId, date));
    }

    private static void printAllMovieSessions() {
        logger.debug("All movie sessions:");
        movieSessionService.getAll().forEach(logger::debug);
    }

    private static void registerAndPrintUser(String email, String password) {
        logger.warn("Registering the user with the e-mail - " + email
                + " and the password - " + password);
        User user;
        try {
            user = authenticationService.register(email, password);
            logger.info("The user " + user + " was registered successfully!");
        } catch (Exception exception) {
            logger.error("Failed to create the user due to using the not unique e-mail", exception);
        }
    }

    private static void loginAndPrintUser(String email, String password) {
        logger.warn("Authenticating the user with the e-mail - " + email
                + " and the password - " + password);
        User user;
        try {
            user = authenticationService.login(email, password);
            logger.info("The user " + user + " was authenticated successfully!");
        } catch (Exception exception) {
            logger.error("Invalid input data", exception);
        }
    }

    private static void printAllUsers() {
        logger.debug("All users:");
        userService.getAll().forEach(logger::debug);
    }

    private static void findAndPrintUserByEmail(String email) {
        logger.debug("Searching for the user with email: " + email);
        logger.debug(userService.findByEmail(email));
    }

    private static void getAndPrintShoppingCartByUser(User user) {
        logger.debug("Searching for the shopping cart by user: " + user);
        logger.debug(shoppingCartService.getByUser(user));
    }

    private static void printAllShoppingCarts() {
        logger.debug("All shopping carts:");
        shoppingCartService.getAll().forEach(logger::debug);
    }

    private static void addSessionToShoppingCart(MovieSession movieSession, User user) {
        shoppingCartService.addSession(movieSession, user);
        logger.info("The movie session - " + movieSession
                + " was added to the shopping cart of the user - " + user);
    }

    private static void clearShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartService.clear(shoppingCart);
        logger.info("The shopping cart with id = " + shoppingCart.getId()
                + " was cleared successfully");
    }

    private static void printAllOrders() {
        logger.debug("All orders:");
        orderService.getAll().forEach(logger::debug);
    }

    private static void completeOrder(ShoppingCart shoppingCart) {
        logger.info("Completing the order of the shopping cart - "
                + shoppingCartService.getByUser(shoppingCart.getUser()));
        Order order = orderService.completeOrder(shoppingCart);
        logger.info("The order: " + order + " of the shopping cart - " + shoppingCart.getId()
                + " was completed successfully");
    }

    private static void printOrderHistoryByUser(User user) {
        logger.debug("User's - " + user + " - orders:");
        orderService.getOrderHistory(user).forEach(logger::debug);
    }
}
