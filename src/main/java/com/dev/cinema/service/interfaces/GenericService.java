package com.dev.cinema.service.interfaces;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);

    T get(Long id);
  
    List<T> getAll();

    void update(T entity);
}
