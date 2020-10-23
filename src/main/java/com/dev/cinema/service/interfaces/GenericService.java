package com.dev.cinema.service.interfaces;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);

    List<T> getAll();

    void update(T entity);
}
