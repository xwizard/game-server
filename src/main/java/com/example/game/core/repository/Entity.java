package com.example.game.core.repository;

/**
 * Entity used in {@link Repository}.
 * @param <ID> type of identifier
 */
public interface Entity<ID> {
  ID getId();
}
