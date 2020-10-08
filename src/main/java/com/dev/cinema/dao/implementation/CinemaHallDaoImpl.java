package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.CinemaHallDao;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.CinemaHall;
import java.util.List;

@Dao
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    @Override
    public List<CinemaHall> getAll() {
        return super.getAll(CinemaHall.class);
    }
}
