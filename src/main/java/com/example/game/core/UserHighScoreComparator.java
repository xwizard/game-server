package com.example.game.core;

import java.util.Comparator;

public class UserHighScoreComparator implements Comparator<UserHighScore> {
  @Override
  public int compare(UserHighScore o1, UserHighScore o2) {
    return o2.getScore().compareTo(o1.getScore());
  }
}
