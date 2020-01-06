package com.pms.dao;

import com.pms.model.userprofile.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Methods provided by CrudRepository.
 *
 * long count();
 * Returns the number of entities available.
 *
 * void delete(T entity)
 * Deletes a given entity.
 *
 * void	deleteAll()
 * Deletes all entities managed by the repository.
 *
 * void deleteAll(Iterable<\? extends T> entities)
 * Deletes the given entities.
 *
 * void deleteById(ID id)
 * Deletes the entity with the given id.
 *
 * boolean existsById(ID id)
 * Returns whether an entity with the given id exists.
 *
 * Iterable<T> findAll()
 * Returns all instances of the type.
 *
 * Iterable<T> findAllById(Iterable<ID> ids)
 * Returns all instances of the type T with the given IDs.
 *
 * Optional<T> findById(ID id)
 * Retrieves an entity by its id.
 *
 * <S extends T> S save(S entity)
 * Saves a given entity.
 *
 * <S extends T> Iterable<S> saveAll(Iterable<S> entities)
 * Saves all given entities.
 *
 * Supports adding new methods e.g.
 * Optional<T> findByUsernameAndEmail(String username, String email)
 *
 * Also, allows to define SQL query a method will execute e.g.
 * \@Query("SELECT * users FROM user)
 * Iterable<T> findAllUsers()
 */

@Repository
public interface UserProfileDao extends CrudRepository<UserProfile, Long> {

    Optional<UserProfile> findByUsername(String username);

    Optional<UserProfile> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}