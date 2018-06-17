package com.example.game.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserScoreTest {
  @Test(expected = IllegalArgumentException.class)
  public void crateShouldThrowOnNullUserId() {
    UserScore.create(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void crateShouldThrowOnNegativeUserId() {
    UserScore.create(-3);
  }

  @Test
  public void createShouldCreateUserScore() {
    UserScore actual = UserScore.create(10);
    assertEquals(Integer.valueOf(10), actual.getId());
  }

  @Test
  public void addLevelScoreShouldAddUserScore() {
    UserScore userScore = UserScore.create(10);
    LevelScore levelScore = LevelScore.of(1, 1);

    userScore.addScore(levelScore);

    LevelScore actual = userScore.getUserSores().get(0);
    assertEquals(levelScore, actual);
  }
}