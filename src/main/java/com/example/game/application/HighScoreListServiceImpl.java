package com.example.game.application;

import com.example.game.core.LevelHighScore;
import com.example.game.core.UserHighScore;
import com.example.game.core.repository.LevelHighScoreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HighScoreListServiceImpl implements HighScoreListService {

  private final LevelHighScoreRepository levelHighScoreRepository;

  public HighScoreListServiceImpl(LevelHighScoreRepository levelHighScoreRepository) {
    this.levelHighScoreRepository = levelHighScoreRepository;
  }

  @Override
  public List<UserHighScore> listHighScores(Integer userId, Integer levelId) {
    Optional<LevelHighScore> levelHighScore = levelHighScoreRepository.get(levelId);
    if (!levelHighScore.isPresent()) {
      return Collections.emptyList();
    }

    return levelHighScore.get().getHighScore(userId);
  }
}
