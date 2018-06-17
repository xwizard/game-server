package com.example.game.core.repository;

import com.example.game.core.UserScore;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserScoreRepository implements UserScoreRepository {

  private Map<Integer, UserScore> userScores = new ConcurrentHashMap<>();

  @Override
  public Optional<UserScore> get(Integer id) {
    return Optional.ofNullable(userScores.get(id));
  }

  @Override
  public void save(UserScore object) {
    userScores.put(object.getId(), object);
  }

  @Override
  public Optional<UserScore> remove(Integer id) {
    return Optional.ofNullable(userScores.remove(id));
  }
}
