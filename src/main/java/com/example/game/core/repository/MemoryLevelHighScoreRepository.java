package com.example.game.core.repository;

import com.example.game.core.LevelHighScore;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryLevelHighScoreRepository implements LevelHighScoreRepository {

  private final Map<Integer, LevelHighScore> scores = new ConcurrentHashMap<>();

  @Override
  public Optional<LevelHighScore> get(Integer id) {
    return Optional.ofNullable(scores.get(id));
  }

  @Override
  public void save(LevelHighScore object) {
    scores.put(object.getId(), object);
  }

  @Override
  public Optional<LevelHighScore> remove(Integer id) {
    return Optional.of(scores.get(id));
  }
}
