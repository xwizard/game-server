package com.example.game.core.repository;

import java.util.Optional;

/**
 * Basic repository for object storage
 * @param <ID> identifier type
 * @param <T> entity managed by repository
 */
public interface Repository<ID, T extends Entity<ID>> {
  /**
   * Retrieves object from repository.
   * @param id identifier
   * @return optional object
   */
  Optional<T> get(ID id);

  /**
   * Stores object in repository.
   * @param object object to store
   */
  void save(T object);

  /**
   * Removes object from repository.
   * @param id identifier
   * @return removed object or empty optional if given object wasn't in repository
   */
  Optional<T> remove(ID id);
}
