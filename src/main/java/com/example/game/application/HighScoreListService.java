package com.example.game.application;

import com.example.game.core.UserHighScore;

import java.util.List;

/**
 * Retrieves high scores.
 */
public interface HighScoreListService {
  /**
   * Lists high scores for given level.
   * @param userId user id
   * @param levelId level id
   * @return high scores
   */
  List<UserHighScore> listHighScores(Integer userId, Integer levelId);
}
