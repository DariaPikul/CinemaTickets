package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.TicketDao;
import com.dev.cinema.model.Ticket;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    protected TicketDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Ticket> getAll() {
        return super.getAll(Ticket.class);
    }
}
