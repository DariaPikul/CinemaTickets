package com.dev.cinema.service;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);

    List<T> getAll();
}
