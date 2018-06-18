package com.example.game.core;

import com.example.game.application.session.SessionId;
import com.example.game.core.repository.Entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Session implements Entity<SessionId> {
  private static final int TEN = 10;
  private final SessionId id;
  private final Integer userId;
  private final LocalDateTime expiryDate;

  // package private for testing purposes
  private Session(SessionId id, Integer userId, LocalDateTime expiryDate) {
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

    return new Session(SessionId.random(), userId, now.plusMinutes(TEN));
  }

  @Override
  public SessionId getId() {
    return id;
  }

  public Integer getUserId() {
    return userId;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  public boolean expired(LocalDateTime now) {
    return ChronoUnit.MINUTES.between(now, getExpiryDate()) < 0;
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
