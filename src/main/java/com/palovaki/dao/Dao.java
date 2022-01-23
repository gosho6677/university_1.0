package com.palovaki.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(Long id);

    List<T> getAll();

    void save(T t);

    void delete(Long id);

    // TODO: update
}
