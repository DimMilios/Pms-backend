package com.pms.dao;

import com.pms.model.userprofile.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Methods provided by CrudRepository.
 * <p>
 * long count();
 * Returns the number of entities available.
 * <p>
 * void delete(T entity)
 * Deletes a given entity.
 * <p>
 * void	deleteAll()
 * Deletes all entities managed by the repository.
 * <p>
 * void deleteAll(Iterable<\? extends T> entities)
 * Deletes the given entities.
 * <p>
 * void deleteById(ID id)
 * Deletes the entity with the given id.
 * <p>
 * boolean existsById(ID id)
 * Returns whether an entity with the given id exists.
 * <p>
 * Iterable<T> findAll()
 * Returns all instances of the type.
 * <p>
 * Iterable<T> findAllById(Iterable<ID> ids)
 * Returns all instances of the type T with the given IDs.
 * <p>
 * Optional<T> findById(ID id)
 * Retrieves an entity by its id.
 * <p>
 * <S extends T> S save(S entity)
 * Saves a given entity.
 * <p>
 * <S extends T> Iterable<S> saveAll(Iterable<S> entities)
 * Saves all given entities.
 * <p>
 * Supports adding new methods e.g.
 * Optional<T> findByUsernameAndEmail(String username, String email)
 * <p>
 * Also, allows to define SQL query a method will execute e.g.
 * \@Query("SELECT * users FROM user)
 * Iterable<T> findAllUsers()
 */
@Repository
public interface UserProfileDao extends CrudRepository<UserProfile, Long> {

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<UserProfile> findByUsername(String username);

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<UserProfile> findByEmail(String email);

}