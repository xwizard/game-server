package com.example.game.core;

public class LevelScore {
  private final Integer levelId;
  private final Integer score;

  private LevelScore(Integer levelId, Integer score) {
    this.levelId = levelId;
    this.score = score;
  }

  public static LevelScore of(Integer levelId, Integer score) {
    if (levelId == null || levelId < 0) throw new IllegalArgumentException("levelId cannot be null or negative.");
    if (score == null || score < 0) throw new IllegalArgumentException("score cannot be null or negative.");

    return new LevelScore(levelId, score);
  }

  public Integer getLevelId() {
    return levelId;
  }

  public Integer getScore() {
    return score;
  }
}
