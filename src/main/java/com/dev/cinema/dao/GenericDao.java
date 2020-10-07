package com.dev.cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T create(T entity);

    List<T> getAll();
}
