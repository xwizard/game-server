package com.example.game.core;

import com.example.game.core.repository.Entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserScore implements Entity<Integer> {

  private final Integer userId;
  private final List<LevelScore> userSores;

  private UserScore(Integer userId, List<LevelScore> userSores) {
    this.userId = userId;
    this.userSores = userSores;
  }

  public static UserScore create(Integer userId) {
    if (userId == null || userId < 0) throw new IllegalArgumentException("User id cannot be null or less then 0.");

    return new UserScore(userId, Collections.synchronizedList(new LinkedList<>()));
  }

  public void addScore(LevelScore levelScore) {
    userSores.add(levelScore);
  }

  @Override
  public Integer getId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserScore userScore = (UserScore) o;
    return Objects.equals(userId, userScore.userId);
  }

  // Only for testing
  List<LevelScore> getUserSores() {
    return userSores;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    return "UserScore{" +
        "userId=" + userId +
        '}';
  }
}
