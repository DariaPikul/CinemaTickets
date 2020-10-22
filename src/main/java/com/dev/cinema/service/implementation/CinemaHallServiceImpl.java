package com.dev.cinema.service.implementation;

import com.dev.cinema.dao.interfaces.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.interfaces.CinemaHallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    @Autowired
    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall create(CinemaHall cinemaHall) {
        return cinemaHallDao.create(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public void update(CinemaHall cinemaHall) {
        cinemaHallDao.update(cinemaHall);
    }
}
