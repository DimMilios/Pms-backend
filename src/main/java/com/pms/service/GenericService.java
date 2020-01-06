package com.pms.service;

import java.util.Optional;

public interface GenericService<T> {

    Iterable<T> getAll();

    Optional<T> getById(Long id);

    Optional<T> create(T body);

    Optional<T> update(T body, Long id);

    void delete(Long id);
}
