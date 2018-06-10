package com.example.game.core;

import com.example.game.core.repository.Entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Session implements Entity<UUID> {
  public static final int TEN = 10;
  private final UUID id;
  private final Integer userId;
  private final LocalDateTime expiryDate;

  // package private for testing purposes
  Session(UUID id, Integer userId, LocalDateTime expiryDate) {
    this.id = id;
    this.userId = userId;
    this.expiryDate = expiryDate;
  }

  /**
   * Creates new session for given user.
   * Session expires in 10 minutes.
   *
   * @param userId user id
   * @param now    time of session creation
   * @return session
   */
  public static Session createFor(Integer userId, LocalDateTime now) {
    if (userId == null || userId.compareTo(0) < 0) throw new IllegalArgumentException("userId cannot be null or less than 0");
    if (now == null) throw new IllegalArgumentException("now cannot be null");

    return new Session(UUID.randomUUID(), userId, now.plusMinutes(TEN));
  }

  @Override
  public UUID getId() {
    return id;
  }

  public Integer getUserId() {
    return userId;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Session session = (Session) o;
    return Objects.equals(id, session.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}