package com.example.game.core.repository;

import com.example.game.application.session.SessionId;
import com.example.game.core.Session;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In memory implementation of {@link SessionRepository}.
 */
public class MemorySessionRepository implements SessionRepository {
  private final Map<SessionId, Session> sessions = new ConcurrentHashMap<>();

  @Override
  public Optional<Session> get(SessionId id) {
    return Optional.ofNullable(sessions.get(id));
  }

  @Override
  public void save(Session object) {
    sessions.put(object.getId(), object);
  }

  @Override
  public Optional<Session> remove(SessionId id) {
    return Optional.ofNullable(sessions.remove(id));
  }
}
