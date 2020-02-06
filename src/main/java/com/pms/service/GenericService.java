package com.pms.service;

import java.util.Optional;

/**
 * The interface Generic service.
 *
 * @param <T> the type parameter
 */
public interface GenericService<T> {

    /**
     * Gets all.
     *
     * @return the all
     */
    Iterable<T> getAll();

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    Optional<T> getById(Long id);

    /**
     * Create optional.
     *
     * @param body the body
     * @return the optional
     */
    Optional<T> create(T body);

    /**
     * Update optional.
     *
     * @param body the body
     * @param id   the id
     * @return the optional
     */
    Optional<T> update(T body, Long id);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);
}
