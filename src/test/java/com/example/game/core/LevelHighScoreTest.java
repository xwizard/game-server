package com.example.game.core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelHighScoreTest {
  @Test(expected = IllegalArgumentException.class)
  public void createShouldThrowOnNull() {
    LevelHighScore.create(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShouldThrowOnNegativeId() {
    LevelHighScore.create(-1);
  }

  @Test
  public void postScoreShouldPost() {
    LevelHighScore highScore = LevelHighScore.create(0);

    highScore.postScore(1, 1);

    assertEquals(1, highScore.getHighScore(1).size());
    assertEquals(new UserHighScore(1, 1), highScore.getHighScore(1).get(0));
  }

  @Test
  public void postScoreShouldSaveOnlyHighestScore() {
    LevelHighScore highScore = LevelHighScore.create(0);

    highScore.postScore(1, 2);
    highScore.postScore(1, 1);

    assertEquals(1, highScore.getHighScore(1).size());
    assertEquals(new UserHighScore(1, 2), highScore.getHighScore(1).get(0));
  }

  @Test
  public void getShouldReturnEmptyHighScore() {
    LevelHighScore highScore = LevelHighScore.create(0);

    highScore.postScore(1, 2);

    assertTrue(highScore.getHighScore(0).isEmpty());
  }

  @Test
  public void getShouldReutrn15HighScores() {
    LevelHighScore highScore = twentyHighScores();

    List<UserHighScore> highScores = highScore.getHighScore(1);
    assertEquals(15, highScores.size());

    for (int i = 6; i < 20; i++) {
      assertTrue(highScores.contains(new UserHighScore(i, i)));
    }
  }

  private LevelHighScore twentyHighScores() {
    LevelHighScore highScore = LevelHighScore.create(0);
    for (int i = 1; i <= 20; i++) {
      highScore.postScore(i, i);
    }
    return highScore;
  }
}