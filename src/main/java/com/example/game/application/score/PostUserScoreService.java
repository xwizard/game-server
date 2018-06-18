package com.example.game.application.score;

/**
 * Stores score for level for user.
 */
public interface PostUserScoreService {
  void post(Integer userId, Integer levelId, Integer score);
}
