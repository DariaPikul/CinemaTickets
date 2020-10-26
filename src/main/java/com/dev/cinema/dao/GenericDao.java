package com.dev.cinema.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    T create(T entity);

    Optional<T> get(Long id, Class<T> clazz);

    List<T> getAll(Class<T> clazz);

    void update(T entity);
}
