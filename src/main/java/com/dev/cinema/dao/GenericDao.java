package com.dev.cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T create(T element);

    List<T> getAll();
}
