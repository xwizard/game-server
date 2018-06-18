package com.example.game.core;

import java.util.Objects;

/**
 * DTO representing high score for user on level.
 */
public class UserHighScore {
  private final Integer userId;
  private final Integer score;

  public UserHighScore(Integer userId, Integer score) {
    this.userId = userId;
    this.score = score;
  }

  public Integer getUserId() {
    return userId;
  }

  public Integer getScore() {
    return score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserHighScore that = (UserHighScore) o;
    return Objects.equals(userId, that.userId) &&
        Objects.equals(score, that.score);
  }

  @Override
  public int hashCode() {

    return Objects.hash(userId, score);
  }

  @Override
  public String toString() {
    return userId + "," + score;
  }
}
