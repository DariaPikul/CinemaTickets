package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.exceptions.DatabaseDataExchangeException;
import com.dev.cinema.model.entity.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    protected MovieSessionDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<MovieSession> getAll(Class<MovieSession> clazz) {
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> getAllMovieSessionsQuery =
                    criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = getAllMovieSessionsQuery.from(MovieSession.class);
            root.fetch("movie", JoinType.INNER);
            root.fetch("cinemaHall", JoinType.INNER);
            getAllMovieSessionsQuery.select(root);
            return session.createQuery(getAllMovieSessionsQuery).getResultList();
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> availableSessionsQuery =
                    criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = availableSessionsQuery.from(MovieSession.class);
            root.fetch("movie", JoinType.INNER);
            root.fetch("cinemaHall", JoinType.INNER);
            Predicate showTimePredicate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(),
                    date.atTime(LocalTime.MAX));
            Predicate moviePredicate = criteriaBuilder.equal(root.get("movie"), movieId);
            availableSessionsQuery.select(root).where(showTimePredicate, moviePredicate);
            return session.createQuery(availableSessionsQuery).getResultList();
        } catch (Exception exception) {
            throw new DatabaseDataExchangeException("Failed to find available sessions "
                    + "by such parameters: "
                    + "movie_id = " + movieId
                    + ", date = " + date, exception);
        }
    }
}
