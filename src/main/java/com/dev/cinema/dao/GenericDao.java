package com.dev.cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T create(T entity);

    void remove(T entity);

    List<T> getAll();
}
