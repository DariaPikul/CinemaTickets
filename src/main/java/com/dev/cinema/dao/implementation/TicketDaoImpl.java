package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.TicketDao;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Ticket;
import java.util.List;

@Dao
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    @Override
    public List<Ticket> getAll() {
        return super.getAll(Ticket.class);
    }
}
