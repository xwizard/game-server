package com.example.game.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class LevelScoreTest {
  @Test(expected = IllegalArgumentException.class)
  public void ofShouldThrowWhenScoreIsNull() {
    LevelScore.of(10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ofShouldThrowWhenLevelIdIsNull() {
    LevelScore.of(null, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ofShouldThrowWhenLevelIdIsLessThan0() {
    LevelScore.of(-1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ofShouldThrowWhenScoreIsLessThan0() {
    LevelScore.of(1, -1);
  }
}