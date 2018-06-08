package com.example.game.core.repository;

import com.example.game.core.Session;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MemorySessionRepository implements SessionRepository {
  private final Map<UUID, Session> sessions = new ConcurrentHashMap<>();

  @Override
  public Optional<Session> get(UUID id) {
    return Optional.ofNullable(sessions.get(id));
  }

  @Override
  public void save(Session object) {
    sessions.put(object.getId(), object);
  }

  @Override
  public Optional<Session> remove(UUID id) {
    return Optional.ofNullable(sessions.remove(id));
  }
}
