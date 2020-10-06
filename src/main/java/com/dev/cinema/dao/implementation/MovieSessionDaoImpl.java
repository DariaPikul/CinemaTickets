package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.exceptions.DatabaseDataExchangeErrorException;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

@Dao
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    protected static final String AVAILABLE_SESSIONS_MESSAGE =
            "Failed to find available sessions by such parameters";

    @Override
    public List<MovieSession> getAll() {
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> getAllMovieSessionsQuery =
                    criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = getAllMovieSessionsQuery.from(MovieSession.class);
            root.fetch("movie", JoinType.INNER);
            root.fetch("cinemaHall", JoinType.INNER);
            getAllMovieSessionsQuery.select(root);
            return session.createQuery(getAllMovieSessionsQuery).getResultList();
        } catch (Exception exception) {
            throw new DatabaseDataExchangeErrorException(GET_ALL_MESSAGE, exception);
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
                    date.atTime(23, 59, 59));
            Predicate moviePredicate = criteriaBuilder.equal(root.get("movie"), movieId);
            availableSessionsQuery.select(root).where(showTimePredicate, moviePredicate);
            return session.createQuery(availableSessionsQuery).getResultList();
        } catch (Exception exception) {
            throw new RuntimeException(AVAILABLE_SESSIONS_MESSAGE, exception);
        }
    }
}
